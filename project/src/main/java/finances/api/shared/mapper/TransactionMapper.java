package finances.api.shared.mapper;

import finances.api.domain.model.Transaction;
import finances.api.infrastructure.database.entity.TransactionEntity;
import finances.api.shared.dto.request.TransactionRequestDTO;
import finances.api.shared.dto.request.TransactionUpdateRequestDTO;
import finances.api.shared.dto.response.TransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "userId", source = "user.id")
    Transaction toDomain(TransactionEntity entity);

    @Mapping(target = "category.id", ignore = true)
    @Mapping(target = "user.id", ignore = true)
    TransactionEntity toCreateTransactionEntity(Transaction domain);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    Transaction toDomain(TransactionRequestDTO dto);

    TransactionResponseDTO toTransactionResponseDTO(Transaction transaction);

    @Mapping(target = "id", source = "transactionId")
    @Mapping(target = "category.id", source = "transactionUpdateRequestDTO.categoryId")
    Transaction toUpdatedTransaction(UUID transactionId, TransactionUpdateRequestDTO transactionUpdateRequestDTO);
}
