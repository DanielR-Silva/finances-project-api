package finances.api.shared.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryTransactionResponseDTO {
    private UUID id;
    private String value;
}
