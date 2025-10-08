package finances.api.application.usecase.transaction;

import finances.api.domain.ports.inbound.transaction.DeleteTransactionUseCase;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;

import java.util.UUID;

public class DeleteTransactionUseCaseImpl implements DeleteTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public DeleteTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public void deleteTransactionById(UUID transactionId) {
        transactionRepositoryPort.deleteById(transactionId);
    }

    @Override
    public void deleteAllTransactionsByUserId(UUID userId) {
        transactionRepositoryPort.deleteAllByUserId(userId);
    }

}
