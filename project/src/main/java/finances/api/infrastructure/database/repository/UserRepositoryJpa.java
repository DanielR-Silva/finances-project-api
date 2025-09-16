package finances.api.infrastructure.database.repository;


import finances.api.infrastructure.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
}
