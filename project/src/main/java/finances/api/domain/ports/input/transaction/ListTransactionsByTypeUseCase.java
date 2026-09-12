package finances.api.domain.ports.input.transaction;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;

import java.util.List;

public interface ListTransactionsByTypeUseCase {
    List<Transaction> execute(TransactionEnum type);
}

