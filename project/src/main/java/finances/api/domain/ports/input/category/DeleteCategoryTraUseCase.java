package finances.api.domain.ports.input.category;

import java.util.UUID;

public interface DeleteCategoryTraUseCase {
    void execute(UUID id);
}
