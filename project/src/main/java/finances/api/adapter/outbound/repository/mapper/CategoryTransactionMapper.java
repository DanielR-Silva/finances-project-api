package finances.api.adapter.outbound.repository.mapper;

import finances.api.domain.model.CategoryTransaction;
import finances.api.infrastructure.database.entity.CategoryTransactionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", implementationName = "OutboundCategoryTransactionMapperImpl")
public interface CategoryTransactionMapper {

    CategoryTransactionEntity toEntity(CategoryTransaction categoryTransaction);

    CategoryTransaction toDomain(CategoryTransactionEntity entity);
}


