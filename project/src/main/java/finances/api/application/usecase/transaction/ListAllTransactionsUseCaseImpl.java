package finances.api.application.usecase.transaction;

import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.ListAllTransactionsUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.List;

public class ListAllTransactionsUseCaseImpl implements ListAllTransactionsUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public ListAllTransactionsUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public List<Transaction> execute() {
        return transactionRepositoryPort.findAll();
    }
}

