package finances.api.domain.ports.inbound.transaction;

import java.util.UUID;

public interface DeleteTransactionUseCase {
    void deleteTransactionById(UUID transactionId);

    void deleteAllTransactionsByUserId(UUID userId);
}
