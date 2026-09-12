package finances.api.domain.ports.input.transaction;

import finances.api.domain.model.Transaction;

import java.util.UUID;

public interface GetTransactionByIdUseCase {
    Transaction execute(UUID transactionId);
}

