package finances.api.application.usecase.user;

import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.ListAllUsersUseCase;
import finances.api.domain.ports.output.UserRepositoryPort;

import java.util.List;

public class ListAllUsersUseCaseImpl implements ListAllUsersUseCase {

    private final UserRepositoryPort userRepository;

    public ListAllUsersUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> execute() {
        return userRepository.findAll();
    }
}

