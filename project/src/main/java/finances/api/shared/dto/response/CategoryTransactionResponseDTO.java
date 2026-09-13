package finances.api.shared.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Category information returned by the API")
public class CategoryTransactionResponseDTO {

    @Schema(description = "Unique identifier from category", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "Category value", example = "Salary")
    private String value;
}
