package finances.api.shared.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDTO {

    @NotNull
    @Positive
    private Double amount;

    @NotNull
    private  String type;

    @NotNull
    private  UUID categoryId;

    @NotNull
    @FutureOrPresent
    private  LocalDate date;


    private String description;

    @NotNull
    private  UUID userId;
}
