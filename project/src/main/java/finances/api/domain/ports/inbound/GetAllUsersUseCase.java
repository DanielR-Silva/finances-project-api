package finances.api.domain.ports.inbound;

import finances.api.domain.model.User;

import java.util.List;

public interface GetAllUsersUseCase {
    List<User> execute();
}
