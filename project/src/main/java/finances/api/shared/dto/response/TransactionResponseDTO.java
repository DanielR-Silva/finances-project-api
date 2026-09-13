package finances.api.shared.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Transaction information returned by the API")
public class TransactionResponseDTO {

    @Schema(description = "Unique identifier from transaction", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Transaction amount", example = "100.50")
    private Double amount;

    @Schema(description = "Transaction type", example = "INCOME")
    private  String type;

    @Schema(description = "Transaction category", example = "Salary")
    private CategoryTransactionResponseDTO category;

    @Schema(description = "Transaction date", example = "2024-06-15")
    private LocalDate date;

    @Schema(description = "Transaction description", example = "Monthly salary payment")
    private String description;

    @Schema(description = "Unique identifier from user associated with the transaction", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID userId;
}
