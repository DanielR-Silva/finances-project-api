package finances.api.domain.ports.outbound;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepositoryPort {
    List<Transaction> findAll();
    List<Transaction> findAllByUserId(UUID userId);
    List<Transaction> findAllByType(TransactionEnum type);
    Optional<Transaction> findById(UUID transactionId);
    boolean existsById(UUID id);
    Transaction update(Transaction updatedTransaction);
    Transaction save(Transaction transaction);
    void deleteById(UUID transactionId);
    void deleteAllByUserId(UUID userId);
}
