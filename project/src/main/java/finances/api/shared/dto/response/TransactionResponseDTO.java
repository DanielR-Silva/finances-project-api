package finances.api.shared.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {

    private Double amount;

    private  String type;

    private CategoryTransactionResponseDTO category;

    private LocalDate date;

    private String description;
}
