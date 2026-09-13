package finances.api.adapter.inbound.handler;

import finances.api.domain.exceptions.EmailAlreadyExistsException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final String ERROR = "error";

    @ExceptionHandler(EntityNotFoundDomainException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(EntityNotFoundDomainException ex) {
        LOGGER.warn("Entity not found: {}", ex.getMessage());
        Map<String, String> error = Map.of(ERROR, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        LOGGER.warn("Email already exists: {}", ex.getMessage());
        Map<String, String> error = Map.of(ERROR, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        LOGGER.warn("Invalid argument: {}", ex.getMessage());
        Map<String, String> error = Map.of(ERROR, ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        LOGGER.error("Unexpected error occurred", ex);
        Map<String, String> error = Map.of(ERROR, "Internal server error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
