package finances.api.shared.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionUpdateRequestDTO {

    private BigDecimal amount;

    private  String type;

    private UUID categoryId;

    private LocalDate date;

    private String description;

    private  UUID userId;
}
