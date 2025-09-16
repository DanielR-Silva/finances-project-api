package finances.api.shared.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    @NotBlank
    private String name;

    @NotBlank
    @Email(message = "Email not valid")
    private String email;

    @NotBlank
    @Length(min = 8, message = "Password must have at least 8 characters")
    private String password;
}
