package finances.api.infrastructure.configuration;

import finances.api.application.usecase.*;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserUseCaseConfiguration {

    @Bean
    public CreateUserUseCaseImpl createUserUseCase(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCaseImpl(userRepository, passwordEncoder);
    }

    @Bean
    public GetUserByIdUseCaseImpl getUserByIdUseCase(UserRepositoryPort userRepository) {
        return new GetUserByIdUseCaseImpl(userRepository);
    }

    @Bean
    public GetAllUsersUseCaseImpl getAllUsersUseCase(UserRepositoryPort userRepository) {
        return new GetAllUsersUseCaseImpl(userRepository);
    }

    @Bean
    public UpdateUserUserCaseImpl updateUserUseCase(UserRepositoryPort userRepository, PasswordEncoder passwordEncoder) {
        return new UpdateUserUserCaseImpl(userRepository, passwordEncoder);
    }

    @Bean
    public DeleteUserUseCaseImpl deleteUserUseCase(UserRepositoryPort userRepository) {
        return new DeleteUserUseCaseImpl(userRepository);
    }
}
