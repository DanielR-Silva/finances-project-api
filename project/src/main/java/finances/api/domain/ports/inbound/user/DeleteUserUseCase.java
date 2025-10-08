package finances.api.domain.ports.inbound.user;

import java.util.UUID;

public interface DeleteUserUseCase {
    void execute(UUID id);
}
