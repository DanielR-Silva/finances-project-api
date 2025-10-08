package finances.api.domain.ports.outbound;

import finances.api.domain.model.CategoryTransaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryTransactionRepositoryPort {
    boolean existsById(UUID id);
    List<CategoryTransaction> findAll();
    Optional<CategoryTransaction> findById(UUID id);
    CategoryTransaction save(CategoryTransaction categoryTransaction);
    CategoryTransaction update(CategoryTransaction updatedCategoryTransaction);
    void deleteById(UUID id);
}
