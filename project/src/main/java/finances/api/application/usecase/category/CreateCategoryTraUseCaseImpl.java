package finances.api.application.usecase.category;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.CreateCategoryTraUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

public class CreateCategoryTraUseCaseImpl implements CreateCategoryTraUseCase {

    private final CategoryTransactionRepositoryPort categoryTraRepository;

    public CreateCategoryTraUseCaseImpl(CategoryTransactionRepositoryPort categoryTraRepository) {
        this.categoryTraRepository = categoryTraRepository;
    }

    @Override
    public CategoryTransaction execute(CategoryTransaction categoryTransaction) {
        return categoryTraRepository.save(categoryTransaction);
    }
}
