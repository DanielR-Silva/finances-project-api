package finances.api.infrastructure.configuration;

import finances.api.application.usecase.transaction.CreateTransactionUseCaseImpl;
import finances.api.application.usecase.transaction.DeleteTransactionUseCaseImpl;
import finances.api.application.usecase.transaction.GetTransactionUseCaseImpl;
import finances.api.application.usecase.transaction.UpdateTransactionUseCaseImpl;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfiguration {

    @Bean
    public CreateTransactionUseCaseImpl createTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new CreateTransactionUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public GetTransactionUseCaseImpl getTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new GetTransactionUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public UpdateTransactionUseCaseImpl updateTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new UpdateTransactionUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public DeleteTransactionUseCaseImpl deleteTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new DeleteTransactionUseCaseImpl(transactionRepositoryPort);
    }
}
