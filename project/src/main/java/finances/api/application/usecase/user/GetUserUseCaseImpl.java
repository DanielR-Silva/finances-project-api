package finances.api.application.usecase.user;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.user.GetUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public class GetUserUseCaseImpl implements GetUserUseCase {

    private final UserRepositoryPort userRepository;

    public GetUserUseCaseImpl(UserRepositoryPort userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(UUID id) {
       return userRepository.findById(id)
               .orElseThrow(
                       () -> new EntityNotFoundException("User not found")
               );
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
}
