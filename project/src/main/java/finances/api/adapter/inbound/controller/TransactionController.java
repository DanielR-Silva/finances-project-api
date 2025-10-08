package finances.api.adapter.inbound.controller;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.domain.ports.inbound.transaction.CreateTransactionUseCase;
import finances.api.domain.ports.inbound.transaction.DeleteTransactionUseCase;
import finances.api.domain.ports.inbound.transaction.GetTransactionUseCase;
import finances.api.domain.ports.inbound.transaction.UpdateTransactionUseCase;
import finances.api.shared.dto.request.TransactionRequestDTO;
import finances.api.shared.dto.request.TransactionUpdateRequestDTO;
import finances.api.shared.dto.response.TransactionResponseDTO;
import finances.api.shared.mapper.TransactionMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

    private final CreateTransactionUseCase createTransactionUseCase;
    private final GetTransactionUseCase getTransactionUseCase;
    private final UpdateTransactionUseCase updateTransactionUseCase;
    private final DeleteTransactionUseCase deleteTransactionUseCase;
    private final TransactionMapper mapper;

    @PostMapping()
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO transaction) {
        Transaction createdTransaction = createTransactionUseCase.execute(mapper.toDomain(transaction));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTransaction.getId())
                .toUri();
        return ResponseEntity.created(location).body(mapper.toTransactionResponseDTO(createdTransaction));
    }

    @GetMapping()
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        List<Transaction> transactions = getTransactionUseCase.getAllTransaction();
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable UUID id) {
        Transaction transaction = getTransactionUseCase.getTransactionById(id);
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(transaction));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsByUserId(@PathVariable UUID userId) {
        List<Transaction> transactions = getTransactionUseCase.getAllTransactionByUserId(userId);
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @GetMapping("/type")
    public ResponseEntity<List<TransactionResponseDTO>> getTransactionsByType(@RequestParam String type) {
        List<Transaction> transactions = getTransactionUseCase.getTransactionsByType(Enum.valueOf(TransactionEnum.class, type.toUpperCase()));
        if (transactions.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                transactions.stream()
                        .map(mapper::toTransactionResponseDTO)
                        .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable UUID id, @RequestBody TransactionUpdateRequestDTO transaction) {
        Transaction updatedTransaction = updateTransactionUseCase.execute(mapper.toUpdatedTransaction(id, transaction));
        return ResponseEntity.ok(mapper.toTransactionResponseDTO(updatedTransaction));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable UUID id) {
        deleteTransactionUseCase.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteAllTransactionsByUserId(@PathVariable UUID userId) {
        deleteTransactionUseCase.deleteAllTransactionsByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
