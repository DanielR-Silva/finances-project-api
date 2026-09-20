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
@Schema(description = "User information returned by the API")
public class UserResponseDTO {

    @Schema(description = "Unique identifier from user", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID id;

    @Schema(description = "User name", example = "John Doe")
    private String name;

    @Schema(description = "User email", example = "johndoe@gmail.com")
    private String email;
}
