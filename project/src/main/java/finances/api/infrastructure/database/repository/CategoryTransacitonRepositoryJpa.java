package finances.api.infrastructure.database.repository;

import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryTransacitonRepositoryJpa extends JpaRepository<CategoryTransactionEntity, UUID> {
}
