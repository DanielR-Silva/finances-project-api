package finances.api.domain.ports.inbound;

import finances.api.domain.model.User;

public interface UpdateUserUseCase {
    User execute(User updatedUser);
}
