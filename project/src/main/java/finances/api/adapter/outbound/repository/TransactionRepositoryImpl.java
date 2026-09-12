package finances.api.adapter.outbound.repository;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.output.TransactionRepositoryPort;
import finances.api.adapter.outbound.repository.mapper.TransactionMapper;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.entity.TransactionEntity;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.TransactionRepositoryJpa;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TransactionRepositoryImpl implements TransactionRepositoryPort {

    private final TransactionRepositoryJpa repository;
    private final EntityManager entityManager;
    private final TransactionMapper mapper;

    @Override
    public List<Transaction> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();

    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId) {
        return repository.findAllByUserId(userId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Transaction> findAllByType(TransactionEnum type) {
        return repository.findAllByType(type).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Optional<Transaction> findById(UUID transactionId) {
        return repository.findById(transactionId).map(mapper::toDomain);
    }

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public Transaction update(Transaction updatedTransaction) {
        TransactionEntity transactionEntity = repository.findById(updatedTransaction.getId())
                .orElseThrow(
                        () -> new EntityNotFoundDomainException("TransactionEntity not found for id " + updatedTransaction.getId())
                );
        Transaction currentTransaction = mapper.toDomain(transactionEntity);
        currentTransaction.applyChangesFrom(updatedTransaction);
        transactionEntity.setAmount(currentTransaction.getAmount());
        transactionEntity.setDescription(currentTransaction.getDescription());
        return mapper.toDomain(repository.save(transactionEntity));
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.toEntity(transaction);
        transactionEntity.setUser(getReferenceUserEntity(transaction.getUserId()));
        transactionEntity.setCategory(getReferenceCategoryTransactionEntity(transaction.getCategory().getId()));
        return mapper.toDomain(repository.save(transactionEntity));
    }

    @Override
    public void deleteById(UUID transactionId) {
        repository.deleteById(transactionId);
    }

    @Override
    public void deleteAllByUserId(UUID userId) {
        repository.deleteAllByUserId(userId);
    }

    private UserEntity getReferenceUserEntity(UUID userId) {
        return entityManager.getReference(UserEntity.class, userId);
    }

    private CategoryTransactionEntity getReferenceCategoryTransactionEntity(UUID categoryId) {
        return entityManager.getReference(CategoryTransactionEntity.class, categoryId);
    }

}
