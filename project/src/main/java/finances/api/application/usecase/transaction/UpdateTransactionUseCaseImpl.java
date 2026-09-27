package finances.api.application.usecase.transaction;

import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.UpdateTransactionUseCase;
import finances.api.domain.ports.output.TransactionRepositoryPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateTransactionUseCaseImpl implements UpdateTransactionUseCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateTransactionUseCaseImpl.class);
    private final TransactionRepositoryPort transactionRepositoryPort;

    public UpdateTransactionUseCaseImpl(TransactionRepositoryPort transactionRepositoryPort) {
        this.transactionRepositoryPort = transactionRepositoryPort;
    }

    @Override
    public Transaction execute(Transaction updatedTransaction) {
            LOGGER.info("Executing update transaction");
            return transactionRepositoryPort.update(updatedTransaction);
    }
}
