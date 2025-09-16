package finances.api.application.usecase;

import finances.api.domain.exceptions.EmailAlreadyExistsException;
import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.CreateUserUseCase;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailAlreadyExistsException("Email already exists");
        user.changePassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
