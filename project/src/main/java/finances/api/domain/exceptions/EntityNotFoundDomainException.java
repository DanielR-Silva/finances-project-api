package finances.api.domain.exceptions;

public class EntityNotFoundDomainException extends RuntimeException {
    public EntityNotFoundDomainException(String message) {
        super(message);
    }
}
