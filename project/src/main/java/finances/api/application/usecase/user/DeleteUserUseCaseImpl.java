package finances.api.application.usecase.user;

import finances.api.domain.ports.inbound.user.DeleteUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepository;

    public DeleteUserUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void execute(UUID id) {
       if (!userRepository.existsById(id)) throw new EntityNotFoundException("User not found");
       userRepository.delete(id);
    }
}
