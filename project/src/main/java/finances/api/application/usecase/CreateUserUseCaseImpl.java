package finances.api.application.usecase;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.CreateUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;

    @Override
    public User execute(User user) {
        return userRepository.save(user);
    }
}
