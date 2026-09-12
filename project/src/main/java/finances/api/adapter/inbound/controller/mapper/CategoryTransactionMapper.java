package finances.api.adapter.inbound.controller.mapper;

import finances.api.domain.model.CategoryTransaction;
import finances.api.shared.dto.request.CategoryTransactionRequestDTO;
import finances.api.shared.dto.request.CategoryTransactionUpdateRequestDTO;
import finances.api.shared.dto.response.CategoryTransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", implementationName = "InboundCategoryTransactionMapperImpl")
public interface CategoryTransactionMapper {

    @Mapping(target = "id", ignore = true)
    CategoryTransaction toDomain(CategoryTransactionRequestDTO dto);

    CategoryTransactionResponseDTO toCategoryTransactionResponseDTO(CategoryTransaction categoryTransaction);

    CategoryTransaction toUpdatedCategoryTransaction(UUID id, CategoryTransactionUpdateRequestDTO categoryTransactionRequestDTO);
}


