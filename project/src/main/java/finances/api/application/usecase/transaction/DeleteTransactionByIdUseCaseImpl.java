package finances.api.application.usecase.transaction;

import finances.api.domain.ports.input.transaction.DeleteTransactionByIdUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.UUID;

public class DeleteTransactionByIdUseCaseImpl implements DeleteTransactionByIdUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public DeleteTransactionByIdUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public void execute(UUID transactionId) {
        transactionRepositoryPort.deleteById(transactionId);
    }
}

