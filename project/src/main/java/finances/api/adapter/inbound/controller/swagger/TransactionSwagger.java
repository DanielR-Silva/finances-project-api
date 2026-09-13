package finances.api.adapter.inbound.controller.swagger;

import finances.api.domain.enums.TransactionEnum;
import finances.api.shared.dto.request.TransactionRequestDTO;
import finances.api.shared.dto.request.TransactionUpdateRequestDTO;
import finances.api.shared.dto.response.TransactionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name="Transaction", description = "Operations related to transaction management")
public interface TransactionSwagger {

    @PostMapping()
    @Operation(summary = "Create a new transaction", description = "Creates a new transaction with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transaction created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "409", description = "Transaction already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO transaction);

    @GetMapping()
    @Operation(summary = "Get all transactions", description = "Retrieves a list of all transactions.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "No transactions found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<TransactionResponseDTO>> getAllTransactions();

    @GetMapping("/{id}")
    @Operation(summary = "Get transaction by ID", description = "Retrieves a transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction retrieved successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<TransactionResponseDTO> getTransactionById(@PathVariable UUID id);

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all transactions by user ID", description = "Retrieves a list of all transactions associated with a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "No transactions found for the user"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<TransactionResponseDTO>> getAllTransactionsByUserId(@PathVariable UUID userId);

    @GetMapping("/type")
    @Operation(summary = "Get all transactions by type", description = "Retrieves a list of all transactions associated with a specific type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transactions retrieved successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionResponseDTO.class)))),
            @ApiResponse(responseCode = "204", description = "No transactions found for the user"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<List<TransactionResponseDTO>> getTransactionsByType(@RequestParam TransactionEnum type);

    @PutMapping("/{id}")
    @Operation(summary = "Update transaction by ID", description = "Updates an existing transaction with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transaction updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    ResponseEntity<TransactionResponseDTO> updateTransaction(@PathVariable UUID id, @Valid @RequestBody TransactionUpdateRequestDTO transaction);

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete transaction by ID", description = "Deletes a transaction by its unique identifier.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transaction deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Transaction not found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<Void> deleteTransaction(@PathVariable UUID id);

    @DeleteMapping("/user/{userId}")
    @Operation(summary = "Delete all transactions by user ID", description = "Deletes all transactions associated with a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Transactions deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "500", description = "API internal error")
    })
    ResponseEntity<Void> deleteAllTransactionsByUserId(@PathVariable UUID userId);
}
