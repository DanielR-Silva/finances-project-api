package finances.api.application.usecase;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.GetUserByIdUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.UUID;

public class GetUserByIdUseCaseImpl  implements GetUserByIdUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserByIdUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(UUID id) {
       return userRepository.findById(id)
               .orElseThrow(
                       () -> new EntityNotFoundException("User not found")
               );
    }
}
