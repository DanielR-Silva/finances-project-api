package finances.api.shared.mapper;

import finances.api.domain.model.CategoryTransaction;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import finances.api.shared.dto.request.CategoryTraUpdateRequestDTO;
import finances.api.shared.dto.request.CategoryTransactionRequestDTO;
import finances.api.shared.dto.response.CategoryTransactionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CategoryTransactionMapper {

    CategoryTransactionEntity toEntity(CategoryTransaction categoryTransaction);

    CategoryTransaction toDomain(CategoryTransactionEntity entity);

    @Mapping(target = "id", ignore = true)
    CategoryTransaction toDomain(CategoryTransactionRequestDTO dto);

    CategoryTransactionResponseDTO toCategoryTransactionResponseDTO(CategoryTransaction categoryTransaction);

    CategoryTransaction toUpdatedCategoryTransaction(UUID id, CategoryTraUpdateRequestDTO categoryTransactionRequestDTO);
}
