package finances.api.domain.ports.input.transaction;

import finances.api.domain.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface ListTransactionsByUserUseCase {
    List<Transaction> execute(UUID userId);
}

