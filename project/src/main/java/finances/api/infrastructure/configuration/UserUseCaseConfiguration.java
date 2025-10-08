package finances.api.infrastructure.configuration;

import finances.api.application.usecase.user.*;
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
    public GetUserUseCaseImpl getUserByIdUseCase(UserRepositoryPort userRepository) {
        return new GetUserUseCaseImpl(userRepository);
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
