package finances.api.application.usecase.transaction;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.GetTransactionByIdUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;

import java.util.UUID;

public class GetTransactionByIdUseCaseImpl implements GetTransactionByIdUseCase {

    private final TransactionRepositoryPort transactionRepositoryPort;

    public GetTransactionByIdUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction execute(UUID transactionId) {
        return transactionRepositoryPort.findById(transactionId)
                .orElseThrow(() -> new EntityNotFoundDomainException("Transaction not found for id " + transactionId));
    }
}

