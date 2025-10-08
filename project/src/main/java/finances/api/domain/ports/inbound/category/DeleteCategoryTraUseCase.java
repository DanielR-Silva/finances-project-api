package finances.api.domain.ports.inbound.category;

import java.util.UUID;

public interface DeleteCategoryTraUseCase {
    void execute(UUID id);
}
