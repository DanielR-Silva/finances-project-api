package finances.api.application.usecase.category;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.UpdateCategoryTransactionUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

public class UpdateCategoryTransactionUseCaseImpl implements UpdateCategoryTransactionUseCase {

    private final CategoryTransactionRepositoryPort categoryRepository;

    public UpdateCategoryTransactionUseCaseImpl(CategoryTransactionRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryTransaction execute(CategoryTransaction updatedCategoryTransaction) {
        if (!categoryRepository.existsById(updatedCategoryTransaction.getId()))
            throw new EntityNotFoundDomainException("Category not found");
        return categoryRepository.save(updatedCategoryTransaction);
    }
}
