package finances.api.domain.ports.input.transaction;

import finances.api.domain.model.Transaction;

public interface UpdateTransactionUseCase {
    Transaction execute(Transaction updatedTransaction);
}
