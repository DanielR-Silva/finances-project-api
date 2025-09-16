package finances.api.domain.ports.outbound;

import finances.api.domain.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryPort {
    boolean existsById(UUID id);
    boolean existsByEmail(String email);
    Optional<User> findById(UUID id);
    List<User> findAll();
    void delete(UUID id);
    User save(User user);
    User update(User updatedUser);
}
