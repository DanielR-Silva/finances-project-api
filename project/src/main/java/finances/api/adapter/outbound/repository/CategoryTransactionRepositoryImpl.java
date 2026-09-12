package finances.api.adapter.outbound.repository;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;
import finances.api.adapter.outbound.repository.mapper.CategoryTransactionMapper;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.repository.CategoryTransactionRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryTransactionRepositoryImpl implements CategoryTransactionRepositoryPort {

    private final CategoryTransactionRepositoryJpa repository;
    private final CategoryTransactionMapper mapper;

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public List<CategoryTransaction> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<CategoryTransaction> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public CategoryTransaction update(CategoryTransaction updatedCategoryTransaction) {
        CategoryTransactionEntity entity = repository.findById(updatedCategoryTransaction.getId())
                .orElseThrow(() -> new EntityNotFoundDomainException("CategoryTransaction not found for id " + updatedCategoryTransaction.getId()));
        CategoryTransaction currentCategoryTransaction = mapper.toDomain(entity);
        currentCategoryTransaction.applyChangesFrom(updatedCategoryTransaction);
        entity.setValue(currentCategoryTransaction.getValue());
        return mapper.toDomain(repository.save(entity));
    }

    @Override
    public CategoryTransaction save(CategoryTransaction categoryTransaction) {
        CategoryTransactionEntity categoryTransactionEntity = repository.save(mapper.toEntity(categoryTransaction));
        return mapper.toDomain(categoryTransactionEntity);
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}
