package finances.api.application.usecase;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.UpdateUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UpdateUserUserCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepository;

    public User execute(User updatedUser) {
        if (userRepository.existsById(updatedUser.getId())) throw new EntityNotFoundException("User not found");
        return userRepository.update(updatedUser);
    }
}
