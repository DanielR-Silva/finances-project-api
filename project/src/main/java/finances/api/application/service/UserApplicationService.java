package finances.api.application.service;

import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.CreateUserUseCase;
import finances.api.domain.ports.input.user.DeleteUserUseCase;
import finances.api.domain.ports.input.user.GetUserByIdUseCase;
import finances.api.domain.ports.input.user.ListAllUsersUseCase;
import finances.api.domain.ports.input.user.UpdateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Application Service (Facade) que agrupa os use cases relacionados ao agregado User.
 * <p>
 * Mantém os controllers enxutos (uma única dependência) sem violar o ISP nos use cases,
 * já que cada porta de entrada continua representando uma única operação.
 */
@Service
@RequiredArgsConstructor
public class UserApplicationService {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final ListAllUsersUseCase listAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @Transactional
    public User create(User user) {
        return createUserUseCase.execute(user);
    }

    @Transactional(readOnly = true)
    public User getById(UUID id) {
        return getUserByIdUseCase.execute(id);
    }

    @Transactional(readOnly = true)
    public List<User> listAll() {
        return listAllUsersUseCase.execute();
    }

    @Transactional
    public User update(User user) {
        return updateUserUseCase.execute(user);
    }

    @Transactional
    public void delete(UUID id) {
        deleteUserUseCase.execute(id);
    }
}

