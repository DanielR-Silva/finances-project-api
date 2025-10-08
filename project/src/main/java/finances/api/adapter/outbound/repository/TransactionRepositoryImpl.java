package finances.api.adapter.outbound.repository;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.entity.TransactionEntity;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.TransactionRepositoryJpa;
import finances.api.shared.mapper.TransactionMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
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
        return repository.findAllByUserId(userId);
    }

    @Override
    public List<Transaction> findAllByType(TransactionEnum type) {
        return repository.findAllByType(type);
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
                        () -> new EntityNotFoundException("TransactionEntity not found for id " + updatedTransaction.getId())
                );
        if (updatedTransaction.getAmount() != null) transactionEntity.setAmount(updatedTransaction.getAmount());
        if (updatedTransaction.getType() != null) transactionEntity.setType(updatedTransaction.getType());
        if (updatedTransaction.getCategory() != null) transactionEntity.setCategory(getReferenceCategoryTransactionEntity(updatedTransaction.getCategory().getId()));
        if (updatedTransaction.getDate() != null) transactionEntity.setDate(updatedTransaction.getDate());
        if (updatedTransaction.getDescription() != null) transactionEntity.setDescription(updatedTransaction.getDescription());
        return mapper.toDomain(repository.save(transactionEntity));
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionEntity = mapper.toCreateTransactionEntity(transaction);
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
