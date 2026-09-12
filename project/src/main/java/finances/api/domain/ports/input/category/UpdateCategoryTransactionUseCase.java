package finances.api.domain.ports.input.category;

import finances.api.domain.model.CategoryTransaction;

public interface UpdateCategoryTransactionUseCase {
    CategoryTransaction execute(CategoryTransaction updatedCategoryTransaction);
}
