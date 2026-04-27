package finances.api.domain.ports.input.transaction;

import java.util.UUID;

public interface DeleteTransactionUseCase {
    void deleteTransactionById(UUID transactionId);

    void deleteAllTransactionsByUserId(UUID userId);
}
