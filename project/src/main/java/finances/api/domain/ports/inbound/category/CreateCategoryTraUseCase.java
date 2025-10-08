package finances.api.domain.ports.inbound.category;

import finances.api.domain.model.CategoryTransaction;

public interface CreateCategoryTraUseCase {
    CategoryTransaction execute(CategoryTransaction categoryTransaction);
}
