package finances.api.shared.mapper;


import finances.api.domain.model.User;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;

import java.util.UUID;

public class UserMapper {
    public static User toExistingUser(UserEntity user) {
        return User.reconstitute(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static UserEntity toCreateUserEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getPassword()
        );
    }

    public static User toNewUser(UserRequestDTO userRequestDTO) {
        return User.create(
                userRequestDTO.getName(),
                userRequestDTO.getEmail(),
                userRequestDTO.getPassword()
        );
    }

    public static User toExistingUser(UUID userId, UserUpdateRequestDTO userRequestDTO) {
        return User.reconstitute(
                userId,
                userRequestDTO.getName(),
                userRequestDTO.getEmail(),
                userRequestDTO.getPassword()
        );
    }

    public static UserResponseDTO toUserResponseDTO(User user) {
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        );
    }
}
