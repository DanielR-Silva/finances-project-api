package finances.api.domain.ports.input.user;

import finances.api.domain.model.User;

public interface UpdateUserUseCase {
    User execute(User updatedUser);
}
