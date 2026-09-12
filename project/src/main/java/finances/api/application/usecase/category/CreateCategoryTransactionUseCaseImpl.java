package finances.api.application.usecase.category;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.CreateCategoryTransactionUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

public class CreateCategoryTransactionUseCaseImpl implements CreateCategoryTransactionUseCase {

    private final CategoryTransactionRepositoryPort categoryTraRepository;

    public CreateCategoryTransactionUseCaseImpl(CategoryTransactionRepositoryPort categoryTraRepository) {
        this.categoryTraRepository = categoryTraRepository;
    }

    @Override
    public CategoryTransaction execute(CategoryTransaction categoryTransaction) {
        return categoryTraRepository.save(categoryTransaction);
    }
}
