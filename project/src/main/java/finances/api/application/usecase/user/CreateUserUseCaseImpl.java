package finances.api.application.usecase.user;

import finances.api.domain.exceptions.EmailAlreadyExistsException;
import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.CreateUserUseCase;
import finances.api.domain.ports.output.PasswordEncoderPort;
import finances.api.domain.ports.output.UserRepositoryPort;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public CreateUserUseCaseImpl(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User user) {
        if (userRepository.existsByEmail(user.getEmail())) throw new EmailAlreadyExistsException("Email already exists");
        user.encryptPassword(passwordEncoder);
        return userRepository.save(user);
    }
}
