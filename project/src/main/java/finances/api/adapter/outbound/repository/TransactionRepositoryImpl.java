package finances.api.adapter.outbound.repository;

import finances.api.adapter.outbound.repository.mapper.TransactionMapper;
import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.exceptions.DataPersistenceException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.output.TransactionRepositoryPort;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.entity.TransactionEntity;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.TransactionRepositoryJpa;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionRepositoryImpl.class);

    @Override
    public List<Transaction> findAll() {
        try{
            LOGGER.info("Execute find all transactions");
            return repository.findAll().stream().map(mapper::toDomain).toList();
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findAll", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Transaction> findAllByUserId(UUID userId) {
        try {
            LOGGER.info("Execute find all transactions by userId: {}", userId);
            return repository.findAllByUserId(userId).stream().map(mapper::toDomain).toList();
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findAllByUserId for UserId: {}", userId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Transaction> findAllByType(TransactionEnum type) {
        try {
            LOGGER.info("Execute find all transactions by type: {}", type);
            return repository.findAllByType(type).stream().map(mapper::toDomain).toList();
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findAllByType for Type: {}", type, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Transaction> findById(UUID transactionId) {
        try {
            LOGGER.info("Execute find transaction by id: {}", transactionId);
            return repository.findById(transactionId).map(mapper::toDomain);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findById for TransactionId: {}", transactionId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existsById(UUID id) {
        try {
            LOGGER.info("Execute exists transaction by id: {}", id);
            return repository.existsById(id);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing exists transaction by id for TransactionId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public Transaction update(Transaction updatedTransaction) {
        try {
            LOGGER.info("Verifying transaction for TransactionId: {}", updatedTransaction.getId());
            TransactionEntity transactionEntity = repository.findById(updatedTransaction.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundDomainException("TransactionEntity not found for id " + updatedTransaction.getId())
                    );
            LOGGER.debug("Transaction to update: {}", updatedTransaction);
            Transaction currentTransaction = mapper.toDomain(transactionEntity);
            currentTransaction.applyChangesFrom(updatedTransaction);
            transactionEntity.setAmount(currentTransaction.getAmount());
            transactionEntity.setDescription(currentTransaction.getDescription());

            LOGGER.info("Execute update transaction");
            return mapper.toDomain(repository.save(transactionEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing update transaction for TransactionId: {}", updatedTransaction.getId(), e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public Transaction save(Transaction transaction) {
        try {
            TransactionEntity transactionEntity = mapper.toEntity(transaction);
            transactionEntity.setUser(getReferenceUserEntity(transaction.getUserId()));
            transactionEntity.setCategory(getReferenceCategoryTransactionEntity(transaction.getCategory().getId()));
            LOGGER.debug("Transaction to save: {}", transaction);
            LOGGER.info("Execute save transaction: {}", transaction);
            return mapper.toDomain(repository.save(transactionEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing save.", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(UUID transactionId) {
        try {
            LOGGER.info("Execute delete transaction by id: {}", transactionId);
            repository.deleteById(transactionId);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing delete transaction by id: {}", transactionId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteAllByUserId(UUID userId) {
        try {
            LOGGER.info("Execute delete all transactions by userId: {}", userId);
            repository.deleteAllByUserId(userId);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing delete all transactions by userId: {}", userId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    private UserEntity getReferenceUserEntity(UUID userId) {
        try {
            LOGGER.info("Execute getReferenceUserEntity for UserId: {}", userId);
            return entityManager.getReference(UserEntity.class, userId);
        }catch (DataAccessException e) {
            LOGGER.error("Error getting reference for UserEntity with id: {}", userId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    private CategoryTransactionEntity getReferenceCategoryTransactionEntity(UUID categoryId) {
        try {
            LOGGER.info("Execute getReferenceCategoryTransactionEntity for CategoryId: {}", categoryId);
            return entityManager.getReference(CategoryTransactionEntity.class, categoryId);
        }catch (DataAccessException e) {
            LOGGER.error("Error getting reference for CategoryEntity with id: {}", categoryId, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }
}
