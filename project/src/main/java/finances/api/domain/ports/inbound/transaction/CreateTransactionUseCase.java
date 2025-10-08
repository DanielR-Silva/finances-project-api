package finances.api.domain.ports.inbound.transaction;

import finances.api.domain.model.Transaction;

public interface CreateTransactionUseCase {
    Transaction execute(Transaction transaction);
}
