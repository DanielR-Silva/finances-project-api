package finances.api.application.usecase;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.GetAllUsersUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;

import java.util.List;

public class GetAllUsersUseCaseImpl implements GetAllUsersUseCase {

    private final UserRepositoryPort userRepository;

    public GetAllUsersUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> execute() {
        return userRepository.findAll();
    }
}
