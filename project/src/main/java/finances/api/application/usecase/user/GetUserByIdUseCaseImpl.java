package finances.api.application.usecase.user;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.GetUserByIdUseCase;
import finances.api.domain.ports.output.UserRepositoryPort;

import java.util.UUID;

public class GetUserByIdUseCaseImpl implements GetUserByIdUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserByIdUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User execute(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundDomainException("User not found"));
    }
}

