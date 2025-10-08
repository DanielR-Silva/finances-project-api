package finances.api.infrastructure.database.entity;

import finances.api.domain.enums.TransactionEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionEnum type;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryTransactionEntity category;

    @Column(nullable = false)
    private LocalDate date;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
