package finances.api.application.usecase.category;

import finances.api.domain.model.CategoryTransaction;
import finances.api.domain.ports.input.category.ListAllCategoryTransactionsUseCase;
import finances.api.domain.ports.output.CategoryTransactionRepositoryPort;

import java.util.List;

public class ListAllCategoryTransactionsUseCaseImpl implements ListAllCategoryTransactionsUseCase {

    private final CategoryTransactionRepositoryPort categoryRepository;

    public ListAllCategoryTransactionsUseCaseImpl(CategoryTransactionRepositoryPort categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryTransaction> execute() {
        return categoryRepository.findAll();
    }
}

