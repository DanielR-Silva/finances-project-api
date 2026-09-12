package finances.api.domain.ports.input.category;

import finances.api.domain.model.CategoryTransaction;

public interface CreateCategoryTransactionUseCase {
    CategoryTransaction execute(CategoryTransaction categoryTransaction);
}
