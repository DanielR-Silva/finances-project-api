package finances.api.adapter.inbound.controller.mapper;

import finances.api.domain.model.User;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring", implementationName = "InboundUserMapperImpl")
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User toNewUser(UserRequestDTO userRequestDTO);

    @Mapping(target = "id", source = "userId")
    User toUser(UUID userId, UserUpdateRequestDTO userUpdateRequestDTO);

    UserResponseDTO toUserResponseDTO(User user);
}


