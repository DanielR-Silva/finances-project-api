package finances.api.shared.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private UUID id;

    private Double amount;

    private  String type;

    private CategoryTransactionResponseDTO category;

    private LocalDate date;

    private String description;
}
