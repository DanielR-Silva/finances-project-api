package finances.api.application.usecase.transaction;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.inbound.transaction.GetTransactionUseCase;
import finances.api.domain.ports.outbound.TransactionRepositoryPort;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public class GetTransactionUseCaseImpl implements GetTransactionUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public GetTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public List<Transaction> getAllTransaction() {
        return transactionRepositoryPort.findAll();
    }

    @Override
    public List<Transaction> getAllTransactionByUserId(UUID userId){
        return transactionRepositoryPort.findAllByUserId(userId);
    }

    @Override
    public Transaction getTransactionById(UUID transactionId) {
        return transactionRepositoryPort.findById(transactionId).orElseThrow(
                () -> new EntityNotFoundException("Transaction not found for id " + transactionId)
        );
    }

    @Override
    public List<Transaction> getTransactionsByType(TransactionEnum type) {
        return transactionRepositoryPort.findAllByType(type);
    }
}
