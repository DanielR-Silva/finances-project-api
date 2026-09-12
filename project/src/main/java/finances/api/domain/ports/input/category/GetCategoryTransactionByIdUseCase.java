package finances.api.domain.ports.input.category;

import finances.api.domain.model.CategoryTransaction;

import java.util.UUID;

public interface GetCategoryTransactionByIdUseCase {
    CategoryTransaction execute(UUID id);
}

