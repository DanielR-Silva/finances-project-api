package finances.api.application.usecase.category;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.inbound.category.GetCategoryTraUseCase;
import finances.api.domain.ports.outbound.CategoryTransactionRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public class GetCategoryTraUseCaseImpl implements GetCategoryTraUseCase {

    private final CategoryTransactionRepositoryPort categoryRepository;

    public GetCategoryTraUseCaseImpl(CategoryTransactionRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryTransaction> getAllCategory() {
        return categoryRepository.findAll();
    }

    public CategoryTransaction getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Category not found with id: " + id)
        );
    }
}
