package finances.api.infrastructure.configuration;

import finances.api.application.usecase.user.CreateUserUseCaseImpl;
import finances.api.application.usecase.user.DeleteUserUseCaseImpl;
import finances.api.application.usecase.user.GetUserByIdUseCaseImpl;
import finances.api.application.usecase.user.ListAllUsersUseCaseImpl;
import finances.api.application.usecase.user.UpdateUserUseCaseImpl;
import finances.api.domain.ports.input.user.CreateUserUseCase;
import finances.api.domain.ports.input.user.DeleteUserUseCase;
import finances.api.domain.ports.input.user.GetUserByIdUseCase;
import finances.api.domain.ports.input.user.ListAllUsersUseCase;
import finances.api.domain.ports.input.user.UpdateUserUseCase;
import finances.api.domain.ports.output.PasswordEncoderPort;
import finances.api.domain.ports.output.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfiguration {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoder) {
        return new CreateUserUseCaseImpl(userRepository, passwordEncoder);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(UserRepositoryPort userRepository) {
        return new GetUserByIdUseCaseImpl(userRepository);
    }

    @Bean
    public ListAllUsersUseCase listAllUsersUseCase(UserRepositoryPort userRepository) {
        return new ListAllUsersUseCaseImpl(userRepository);
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(UserRepositoryPort userRepository, PasswordEncoderPort passwordEncoder) {
        return new UpdateUserUseCaseImpl(userRepository, passwordEncoder);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(UserRepositoryPort userRepository) {
        return new DeleteUserUseCaseImpl(userRepository);
    }
}
