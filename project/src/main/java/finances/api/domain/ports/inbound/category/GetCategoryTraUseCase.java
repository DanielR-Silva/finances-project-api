package finances.api.domain.ports.inbound.category;

import finances.api.domain.model.CategoryTransaction;

import java.util.List;
import java.util.UUID;

public interface GetCategoryTraUseCase {
    List<CategoryTransaction> getAllCategory();

    CategoryTransaction getById(UUID id);
}
