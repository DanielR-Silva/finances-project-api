package finances.api.adapter.outbound.repository.mapper;

import finances.api.domain.model.User;
import finances.api.infrastructure.database.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", implementationName = "OutboundUserMapperImpl")
public interface UserMapper {

    User toDomain(UserEntity user);

    @Mapping(target = "transactions", ignore = true)
    UserEntity toEntity(User user);
}


