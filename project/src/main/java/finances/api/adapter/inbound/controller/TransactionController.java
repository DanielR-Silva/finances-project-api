package finances.api.adapter.inbound.controller;

import finances.api.application.service.TransactionApplicationService;
import finances.api.adapter.inbound.controller.mapper.TransactionMapper;
import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.shared.dto.request.TransactionRequestDTO;
import finances.api.shared.dto.request.TransactionUpdateRequestDTO;
import finances.api.shared.dto.response.TransactionResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionApplicationService transactionService;
    private final TransactionMapper mapper;

    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO transaction) {
        LOGGER.info("Creating transaction");
        Transaction createdTransaction = transactionService.create(mapper.toDomain(transaction));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransaction.getId())
                .toUri();
        LOGGER.info("Created transaction with id: {}", createdTransaction.getId());
        LOGGER.debug("Transaction: {}", createdTransaction);
        return ResponseEntity.created(location).body(mapper.toTransactionResponseDTO(createdTransaction));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        LOGGER.info("Get all transactions");
        List<Transaction> transactions = transactionService.listAll();
        LOGGER.info("Transactions count: {}", transactions.size());
        LOGGER.debug("Transactions: {}", transactions);
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable UUID id) {
        LOGGER.info("Get transaction by id: {}", id);
        Transaction transaction = transactionService.getById(id);
        LOGGER.info("Transaction found");
        LOGGER.debug("Transaction: {}", transaction);
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(transaction));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsByUserId(@PathVariable UUID userId) {
        LOGGER.info("Get all transactions by user id: {}", userId);
        List<Transaction> transactions = transactionService.listByUser(userId);
        LOGGER.info("Transactions count: {}", transactions.size());
        LOGGER.debug("Transactions: {}", transactions);
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/type")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByType(@RequestParam TransactionEnum type) {
        LOGGER.info("Get transactions by type: {}", type);
        List<Transaction> transactions = transactionService.listByType(type);
        LOGGER.info("Transactions count: {}", transactions.size());
        LOGGER.debug("Transactions: {}", transactions);
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable UUID id, @Valid @RequestBody TransactionUpdateRequestDTO transaction) {
        LOGGER.info("Update transaction");
        Transaction updatedTransaction = transactionService.update(mapper.toUpdatedTransaction(id, transaction));
        LOGGER.info("Updated transaction");
        LOGGER.debug("Updated Transaction: {}", updatedTransaction);
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        LOGGER.info("Deleting transaction with id: {}", id);
        transactionService.deleteById(id);
        LOGGER.info("Deleted transaction");
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteAllTransactionsByUserId(@PathVariable UUID userId) {
        LOGGER.info("Deleting all transactions by user id: {}", userId);
        transactionService.deleteAllByUser(userId);
        LOGGER.info("Deleted all transactions by user id");
        return ResponseEntity.noContent().build();
    }
}
