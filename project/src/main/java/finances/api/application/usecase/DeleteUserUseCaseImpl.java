package finances.api.application.usecase;

import finances.api.domain.ports.inbound.DeleteUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepositoryPort userRepository;

    public void execute(UUID id) {
       if (userRepository.existsById(id)) throw new EntityNotFoundException("User not found");
       userRepository.delete(id);
    }
}
