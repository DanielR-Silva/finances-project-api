package finances.api.domain.ports.inbound;

import finances.api.domain.model.User;

public interface CreateUserUseCase {
    User execute(User user);
}
