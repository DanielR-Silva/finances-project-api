package finances.api.adapter.inbound.controller.mapper;

import finances.api.domain.model.Transaction;
import finances.api.shared.dto.request.TransactionRequestDTO;
import finances.api.shared.dto.request.TransactionUpdateRequestDTO;
import finances.api.shared.dto.response.TransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", implementationName = "InboundTransactionMapperImpl")
public interface TransactionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "category.id", source = "categoryId")
    Transaction toDomain(TransactionRequestDTO dto);

    TransactionResponseDTO toTransactionResponseDTO(Transaction transaction);

    @Mapping(target = "id", source = "transactionId")
    @Mapping(target = "category.id", source = "transactionUpdateRequestDTO.categoryId")
    Transaction toUpdatedTransaction(UUID transactionId, TransactionUpdateRequestDTO transactionUpdateRequestDTO);
}


