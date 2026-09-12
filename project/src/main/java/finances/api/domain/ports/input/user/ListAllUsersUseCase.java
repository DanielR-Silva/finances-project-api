package finances.api.domain.ports.input.user;

import finances.api.domain.model.User;

import java.util.List;

public interface ListAllUsersUseCase {
    List<User> execute();
}

