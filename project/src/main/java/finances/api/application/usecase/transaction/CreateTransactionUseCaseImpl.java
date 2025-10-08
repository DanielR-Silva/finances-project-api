package finances.api.application.usecase.transaction;

import finances.api.domain.model.Transaction;
import finances.api.domain.ports.inbound.transaction.CreateTransactionUseCase;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;

public class CreateTransactionUseCaseImpl implements CreateTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public CreateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction execute(Transaction transaction) {
        return transactionRepositoryPort.save(transaction);
    }

}
