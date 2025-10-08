package finances.api.application.usecase.category;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.inbound.category.UpdateCategoryTraUseCase;
import finances.api.domain.ports.outbound.CategoryTransactionRepositoryPort;

public class UpdateCategoryTraUseCaseImpl implements UpdateCategoryTraUseCase {

    private final CategoryTransactionRepositoryPort categoryRepository;

    public UpdateCategoryTraUseCaseImpl(CategoryTransactionRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryTransaction execute(CategoryTransaction updatedCategoryTransaction) {
        if (!categoryRepository.existsById(updatedCategoryTransaction.getId()))
            throw new jakarta.persistence.EntityNotFoundException("Category not found");
        return categoryRepository.save(updatedCategoryTransaction);
    }
}
