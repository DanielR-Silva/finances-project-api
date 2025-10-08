package finances.api.adapter.outbound.repository;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.outbound.CategoryTransactionRepositoryPort;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.infrastructure.database.repository.CategoryTransacitonRepositoryJpa;
import finances.api.shared.mapper.CategoryTransactionMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CategoryTransactionRepositoryImpl implements CategoryTransactionRepositoryPort {

    private final CategoryTransacitonRepositoryJpa repository;
    private final CategoryTransactionMapper mapper;

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public List<CategoryTransaction> findAll() {
        return repository.findAll().stream()
                .map(entity -> new CategoryTransaction(entity.getId(), entity.getValue()))
                .toList();
    }

    @Override
    public Optional<CategoryTransaction> findById(UUID id) {
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public CategoryTransaction update(CategoryTransaction updatedCategoryTransaction) {
        CategoryTransactionEntity entity = repository.findById(updatedCategoryTransaction.getId())
                .orElseThrow(() -> new EntityNotFoundException("CategoryTransaction not found for id " + updatedCategoryTransaction.getId()));
        if (updatedCategoryTransaction.getValue() != null) entity.setValue(updatedCategoryTransaction.getValue());
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
