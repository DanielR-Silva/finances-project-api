package finances.api.infrastructure.database.repository;

import finances.api.domain.enums.TransactionEnum;
import finances.api.domain.model.Transaction;
import finances.api.infrastructure.database.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepositoryJpa extends JpaRepository<TransactionEntity, UUID> {
    List<Transaction> findAllByUserId(UUID userId);

    List<Transaction> findAllByType(TransactionEnum type);

    void deleteAllByUserId(UUID userId);
}
