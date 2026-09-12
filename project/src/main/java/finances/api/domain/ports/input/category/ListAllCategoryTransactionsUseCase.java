package finances.api.domain.ports.input.category;

import finances.api.domain.model.CategoryTransaction;

import java.util.List;

public interface ListAllCategoryTransactionsUseCase {
    List<CategoryTransaction> execute();
}

