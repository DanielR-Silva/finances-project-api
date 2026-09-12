package finances.api.application.usecase.transaction;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.ListTransactionsByTypeUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.List;

public class ListTransactionsByTypeUseCaseImpl implements ListTransactionsByTypeUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public ListTransactionsByTypeUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public List<Transaction> execute(TransactionEnum type) {
        return transactionRepositoryPort.findAllByType(type);
    }
}

