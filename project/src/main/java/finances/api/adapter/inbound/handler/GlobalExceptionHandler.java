package finances.api.adapter.inbound.handler;

import finances.api.domain.exceptions.DataPersistenceException;
import finances.api.domain.exceptions.EmailAlreadyExistsException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
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
        Map<String, String> error = Map.of(ERROR, "Entity not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex) {
        LOGGER.warn("Email already exists: {}", ex.getMessage());
        Map<String, String> error = Map.of(ERROR, "Email already exists");
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataPersistenceException.class)
    public ResponseEntity<Map<String, String>> handleDataPersistenceException(DataPersistenceException ex) {
        LOGGER.error("Data persistence error", ex);
        Map<String, String> error = Map.of(ERROR, "An internal persistence error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        LOGGER.warn("Invalid argument: {}", ex.getMessage());
        Map<String, String> error = Map.of(ERROR, "Invalid argument");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        LOGGER.error("Unexpected error occurred", ex);
        Map<String, String> error = Map.of(ERROR, "Internal server error");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, String>> handleDataAccessException(DataAccessException ex) {
        LOGGER.error("Data access error", ex);
        Map<String, String> error = Map.of(ERROR,"An internal persistence error occurred");
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
