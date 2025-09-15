package finances.api.infrastructure.configuration;

import finances.api.application.usecase.CreateUserUseCaseImpl;
import finances.api.application.usecase.DeleteUserUseCaseImpl;
import finances.api.application.usecase.GetUserByIdUseCaseImpl;
import finances.api.application.usecase.UpdateUserUserCaseImpl;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfiguration {

    @Bean
    public CreateUserUseCaseImpl createUserUseCase(UserRepositoryPort userRepository) {
        return new CreateUserUseCaseImpl(userRepository);
    }

    @Bean
    public GetUserByIdUseCaseImpl getUserByIdUseCase(UserRepositoryPort userRepository) {
        return new GetUserByIdUseCaseImpl(userRepository);
    }

    @Bean
    public UpdateUserUserCaseImpl updateUserUseCase(UserRepositoryPort userRepository) {
        return new UpdateUserUserCaseImpl(userRepository);
    }

    @Bean
    public DeleteUserUseCaseImpl deleteUserUseCase(UserRepositoryPort userRepository) {
        return new DeleteUserUseCaseImpl(userRepository);
    }
}
