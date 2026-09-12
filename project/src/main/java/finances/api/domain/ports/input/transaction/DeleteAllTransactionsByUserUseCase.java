package finances.api.domain.ports.input.transaction;

import java.util.UUID;

public interface DeleteAllTransactionsByUserUseCase {
    void execute(UUID userId);
}

