package finances.api.adapter.inbound.handler;

import finances.api.domain.exceptions.DataPersistenceException;
import finances.api.domain.exceptions.EmailAlreadyExistsException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.shared.dto.response.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundDomainException.class)
    public ResponseEntity<ErrorResponseDTO> handleEntityNotFoundException(EntityNotFoundDomainException ex) {
        LOGGER.warn("Entity not found: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Entity not found");
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        LOGGER.warn("Email already exists: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.CONFLICT, "Email already exists");
    }

    @ExceptionHandler(DataPersistenceException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataPersistenceException(DataPersistenceException ex) {
        LOGGER.error("Data persistence error", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An internal persistence error occurred");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex) {
        LOGGER.warn("Invalid argument: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid argument");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception ex) {
        LOGGER.error("Unexpected error occurred", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataAccessException(DataAccessException ex) {
        LOGGER.error("Data access error", ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "An internal persistence error occurred");
    }

    private ResponseEntity<ErrorResponseDTO> buildErrorResponse(HttpStatus status, String message) {
        ErrorResponseDTO errorResponse = ErrorResponseDTO.builder()
                .status(status.value())
                .message(message)
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .build();

        return new ResponseEntity<>(errorResponse, status);
    }
}
