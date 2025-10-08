package finances.api.shared.mapper;


import finances.api.domain.model.User;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUpdatedUser(UserEntity user);

    @Mapping(target = "transactions", ignore = true)
    UserEntity toCreateUserEntity(User user);

    User toNewUser(UserRequestDTO userRequestDTO);

    User toUpdatedUser(UUID userId, UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);
}
