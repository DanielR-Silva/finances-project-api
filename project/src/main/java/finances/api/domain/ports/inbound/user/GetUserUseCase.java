package finances.api.domain.ports.inbound.user;

import finances.api.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface GetUserUseCase {
    User getUserById(UUID id);
    List<User> getAllUser();
}
