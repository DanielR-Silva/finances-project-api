package finances.api.domain.ports.input.user;

import java.util.UUID;

public interface DeleteUserUseCase {
    void execute(UUID id);
}
