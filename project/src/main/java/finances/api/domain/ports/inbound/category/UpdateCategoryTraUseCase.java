package finances.api.domain.ports.inbound.category;

import finances.api.domain.model.CategoryTransaction;

public interface UpdateCategoryTraUseCase {
    CategoryTransaction execute(CategoryTransaction updatedCategoryTransaction);
}
