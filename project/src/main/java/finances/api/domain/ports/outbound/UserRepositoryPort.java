package finances.api.domain.ports.outbound;

import finances.api.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    boolean existsById(UUID id);
    Optional<User> findById(UUID id);
    void delete(UUID id);
    User save(User user);
    User update(User updatedUser);
}
