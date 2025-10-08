package finances.api.domain.ports.inbound.transaction;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;

import java.util.List;
import java.util.UUID;

public interface GetTransactionUseCase {
    List<Transaction> getAllTransaction();

    List<Transaction> getAllTransactionByUserId(UUID userId);

    Transaction getTransactionById(UUID transactionId);

    List<Transaction> getTransactionsByType(TransactionEnum type);
}
