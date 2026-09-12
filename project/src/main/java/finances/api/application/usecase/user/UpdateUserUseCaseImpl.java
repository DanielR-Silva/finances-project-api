package finances.api.application.usecase.user;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.User;
import finances.api.domain.ports.input.user.UpdateUserUseCase;
import finances.api.domain.ports.output.PasswordEncoderPort;
import finances.api.domain.ports.output.UserRepositoryPort;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepositoryPort userRepository;
    private final PasswordEncoderPort passwordEncoder;

    public UpdateUserUseCaseImpl(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User execute(User updatedUser) {
        if (!userRepository.existsById(updatedUser.getId())) throw new EntityNotFoundDomainException("User not found");
        updatedUser.encryptPassword(passwordEncoder);
        return userRepository.update(updatedUser);
    }
}
