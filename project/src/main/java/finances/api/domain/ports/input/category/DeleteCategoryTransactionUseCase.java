package finances.api.domain.ports.input.category;

import java.util.UUID;

public interface DeleteCategoryTransactionUseCase {
    void execute(UUID id);
}
