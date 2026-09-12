package finances.api.infrastructure.configuration;

import finances.api.application.usecase.transaction.CreateTransactionUseCaseImpl;
import finances.api.application.usecase.transaction.DeleteAllTransactionsByUserUseCaseImpl;
import finances.api.application.usecase.transaction.DeleteTransactionByIdUseCaseImpl;
import finances.api.application.usecase.transaction.GetTransactionByIdUseCaseImpl;
import finances.api.application.usecase.transaction.ListAllTransactionsUseCaseImpl;
import finances.api.application.usecase.transaction.ListTransactionsByTypeUseCaseImpl;
import finances.api.application.usecase.transaction.ListTransactionsByUserUseCaseImpl;
import finances.api.application.usecase.transaction.UpdateTransactionUseCaseImpl;
import finances.api.domain.ports.input.transaction.CreateTransactionUseCase;
import finances.api.domain.ports.input.transaction.DeleteAllTransactionsByUserUseCase;
import finances.api.domain.ports.input.transaction.DeleteTransactionByIdUseCase;
import finances.api.domain.ports.input.transaction.GetTransactionByIdUseCase;
import finances.api.domain.ports.input.transaction.ListAllTransactionsUseCase;
import finances.api.domain.ports.input.transaction.ListTransactionsByTypeUseCase;
import finances.api.domain.ports.input.transaction.ListTransactionsByUserUseCase;
import finances.api.domain.ports.input.transaction.UpdateTransactionUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TransactionUseCaseConfiguration {

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new CreateTransactionUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public GetTransactionByIdUseCase getTransactionByIdUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new GetTransactionByIdUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public ListAllTransactionsUseCase listAllTransactionsUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new ListAllTransactionsUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public ListTransactionsByUserUseCase listTransactionsByUserUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new ListTransactionsByUserUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public ListTransactionsByTypeUseCase listTransactionsByTypeUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new ListTransactionsByTypeUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public UpdateTransactionUseCase updateTransactionUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new UpdateTransactionUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public DeleteTransactionByIdUseCase deleteTransactionByIdUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new DeleteTransactionByIdUseCaseImpl(transactionRepositoryPort);
    }

    @Bean
    public DeleteAllTransactionsByUserUseCase deleteAllTransactionsByUserUseCase(TransactionRepositoryPort transactionRepositoryPort) {
        return new DeleteAllTransactionsByUserUseCaseImpl(transactionRepositoryPort);
    }
}
