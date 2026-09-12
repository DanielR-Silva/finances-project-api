package finances.api.domain.ports.input.transaction;

import java.util.UUID;

public interface DeleteTransactionByIdUseCase {
    void execute(UUID transactionId);
}

