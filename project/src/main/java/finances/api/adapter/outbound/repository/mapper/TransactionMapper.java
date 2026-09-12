package finances.api.adapter.outbound.repository.mapper;

import finances.api.domain.model.Transaction;
import finances.api.infrastructure.database.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "OutboundTransactionMapperImpl")
public interface TransactionMapper {

    @Mapping(target = "userId", source = "user.id")
    Transaction toDomain(TransactionEntity entity);

    @Mapping(target = "category.id", ignore = true)
    @Mapping(target = "user.id", ignore = true)
    TransactionEntity toEntity(Transaction domain);
}


