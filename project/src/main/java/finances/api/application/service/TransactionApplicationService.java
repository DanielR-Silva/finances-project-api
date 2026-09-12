package finances.api.application.service;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.input.transaction.CreateTransactionUseCase;
import finances.api.domain.ports.input.transaction.DeleteAllTransactionsByUserUseCase;
import finances.api.domain.ports.input.transaction.DeleteTransactionByIdUseCase;
import finances.api.domain.ports.input.transaction.GetTransactionByIdUseCase;
import finances.api.domain.ports.input.transaction.ListAllTransactionsUseCase;
import finances.api.domain.ports.input.transaction.ListTransactionsByTypeUseCase;
import finances.api.domain.ports.input.transaction.ListTransactionsByUserUseCase;
import finances.api.domain.ports.input.transaction.UpdateTransactionUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Application Service (Facade) que agrupa os use cases relacionados ao agregado Transaction.
 * <p>
 * Mantém os controllers enxutos (uma única dependência) sem violar o ISP nos use cases,
 * já que cada porta de entrada continua representando uma única operação.
 */
@Service
@RequiredArgsConstructor
public class TransactionApplicationService {

    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionByIdUseCase getTransactionByIdUseCase;
    private final ListAllTransactionsUseCase listAllTransactionsUseCase;
    private final ListTransactionsByUserUseCase listTransactionsByUserUseCase;
    private final ListTransactionsByTypeUseCase listTransactionsByTypeUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionByIdUseCase deleteTransactionByIdUseCase;
    private final DeleteAllTransactionsByUserUseCase deleteAllTransactionsByUserUseCase;

    @Transactional
    public Transaction create(Transaction transaction) {
        return createTransactionUseCase.execute(transaction);
    }

    @Transactional(readOnly = true)
    public Transaction getById(UUID transactionId) {
        return getTransactionByIdUseCase.execute(transactionId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> listAll() {
        return listAllTransactionsUseCase.execute();
    }

    @Transactional(readOnly = true)
    public List<Transaction> listByUser(UUID userId) {
        return listTransactionsByUserUseCase.execute(userId);
    }

    @Transactional(readOnly = true)
    public List<Transaction> listByType(TransactionEnum type) {
        return listTransactionsByTypeUseCase.execute(type);
    }

    @Transactional
    public Transaction update(Transaction transaction) {
        return updateTransactionUseCase.execute(transaction);
    }

    @Transactional
    public void deleteById(UUID transactionId) {
        deleteTransactionByIdUseCase.execute(transactionId);
    }

    @Transactional
    public void deleteAllByUser(UUID userId) {
        deleteAllTransactionsByUserUseCase.execute(userId);
    }
}

