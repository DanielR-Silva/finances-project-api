package finances.api.application.usecase.transaction;

import finances.api.domain.ports.input.transaction.DeleteAllTransactionsByUserUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.UUID;

public class DeleteAllTransactionsByUserUseCaseImpl implements DeleteAllTransactionsByUserUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public DeleteAllTransactionsByUserUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public void execute(UUID userId) {
        transactionRepositoryPort.deleteAllByUserId(userId);
    }
}

