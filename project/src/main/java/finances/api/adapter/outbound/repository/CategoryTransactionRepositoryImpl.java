package finances.api.adapter.outbound.repository;

import finances.api.domain.exceptions.DataPersistenceException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;
import finances.api.adapter.outbound.repository.mapper.CategoryTransactionMapper;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.repository.CategoryTransactionRepositoryJpa;
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
public class CategoryTransactionRepositoryImpl implements CategoryTransactionRepositoryPort {

    private final CategoryTransactionRepositoryJpa repository;
    private final CategoryTransactionMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryTransactionRepositoryImpl.class);

    @Override
    public boolean existsById(UUID id) {
        try {
            LOGGER.info("Executing exists category transaction by id: {}", id);
            return repository.existsById(id);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing exists by id for CategoryTransactionId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<CategoryTransaction> findAll() {
        try {
            LOGGER.info("Executing findAll category transactions");
            return repository.findAll().stream()
                    .map(mapper::toDomain)
                    .toList();
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findAll category transactions", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<CategoryTransaction> findById(UUID id) {
        try {
            LOGGER.info("Executing find category transaction by id: {}", id);
            return repository.findById(id).map(mapper::toDomain);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing findById for CategoryTransactionId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public CategoryTransaction update(CategoryTransaction updatedCategoryTransaction) {
        try {
            LOGGER.info("Verifying existence of category transaction by id: {}", updatedCategoryTransaction.getId());
            CategoryTransactionEntity entity = repository.findById(updatedCategoryTransaction.getId())
                    .orElseThrow(() -> new EntityNotFoundDomainException("CategoryTransaction not found for id " + updatedCategoryTransaction.getId()));
            LOGGER.debug("CategoryTransaction to update: {}", updatedCategoryTransaction);
            CategoryTransaction currentCategoryTransaction = mapper.toDomain(entity);
            currentCategoryTransaction.applyChangesFrom(updatedCategoryTransaction);
            CategoryTransactionEntity updatedCategoryTransactionEntity = mapper.toEntity(currentCategoryTransaction);

            LOGGER.info("Executing update category");
            return mapper.toDomain(repository.save(updatedCategoryTransactionEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing update category for CategoryTransactionId: {}", updatedCategoryTransaction.getId(), e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public CategoryTransaction save(CategoryTransaction categoryTransaction) {
        try {
            CategoryTransactionEntity categoryTransactionEntity = mapper.toEntity(categoryTransaction);
            LOGGER.debug("CategoryTransaction to save: {}", categoryTransaction);
            LOGGER.info("Executing save category");
            return mapper.toDomain(repository.save(categoryTransactionEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing save category transaction", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            LOGGER.info("Executing delete category transaction by id: {}", id);
            repository.deleteById(id);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing delete category transaction by id for CategoryTransactionId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }
}
