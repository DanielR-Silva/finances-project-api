package finances.api.shared.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
@Schema(description = "Error response returned by the API")
public record ErrorResponseDTO (
        @Schema(description = "HTTP status code of the error", example = "404")
        int status,

        @Schema(description = "Error message", example = "Entity not found")
        String message,

        @Schema(description = "Timestamp of the error occurrence", example = "2023-10-05T14:48:00")
        LocalDateTime timestamp
){
}
