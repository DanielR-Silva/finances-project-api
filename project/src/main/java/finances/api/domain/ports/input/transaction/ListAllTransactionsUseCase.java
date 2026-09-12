package finances.api.domain.ports.input.transaction;

import finances.api.domain.model.Transaction;

import java.util.List;

public interface ListAllTransactionsUseCase {
    List<Transaction> execute();
}

