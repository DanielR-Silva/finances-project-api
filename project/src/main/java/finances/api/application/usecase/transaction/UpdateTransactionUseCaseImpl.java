package finances.api.application.usecase.transaction;

import finances.api.domain.model.Transaction;
import finances.api.domain.ports.inbound.transaction.UpdateTransactionUseCase;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

public class UpdateTransactionUseCaseImpl implements UpdateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public UpdateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction execute(Transaction updatedTransaction) {
        if (!transactionRepositoryPort.existsById(updatedTransaction.getId())) throw new EntityNotFoundException("Transaction not found");

        return transactionRepositoryPort.update(updatedTransaction);
    }
}
