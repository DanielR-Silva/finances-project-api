package finances.api.domain.ports.inbound.user;

import finances.api.domain.model.User;

public interface CreateUserUseCase {
    User execute(User user);
}
