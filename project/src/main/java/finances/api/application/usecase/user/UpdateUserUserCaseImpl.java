package finances.api.application.usecase.user;

import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.UpdateUserUseCase;
import finances.api.domain.ports.output.UserRepositoryPort;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserUserCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepository;

    private final PasswordEncoder passwordEncoder;

    public UpdateUserUserCaseImpl(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User updatedUser) {
        if (!userRepository.existsById(updatedUser.getId())) throw new EntityNotFoundException("User not found");
        updatedUser.changePassword(passwordEncoder.encode(updatedUser.getPassword()));
        return userRepository.update(updatedUser);
    }
}
