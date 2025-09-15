package finances.api.domain.ports.inbound;

import finances.api.domain.model.User;

import java.util.UUID;

public interface GetUserByIdUseCase {
    User execute(UUID id);
}
