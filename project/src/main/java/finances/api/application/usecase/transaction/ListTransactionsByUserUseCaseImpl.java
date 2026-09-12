package finances.api.application.usecase.transaction;

import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.ListTransactionsByUserUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.List;
import java.util.UUID;

public class ListTransactionsByUserUseCaseImpl implements ListTransactionsByUserUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public ListTransactionsByUserUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public List<Transaction> execute(UUID userId) {
        return transactionRepositoryPort.findAllByUserId(userId);
    }
}

