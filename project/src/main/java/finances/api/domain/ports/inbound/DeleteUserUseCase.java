package finances.api.domain.ports.inbound;

import java.util.UUID;

public interface DeleteUserUseCase {
    void execute(UUID id);
}
