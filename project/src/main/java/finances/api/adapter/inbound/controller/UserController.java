package finances.api.adapter.inbound.controller;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.CreateUserUseCase;
import finances.api.domain.ports.inbound.DeleteUserUseCase;
import finances.api.domain.ports.inbound.GetUserByIdUseCase;
import finances.api.domain.ports.inbound.UpdateUserUseCase;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;
import finances.api.shared.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) {
        User createdUser = createUserUseCase.execute(UserMapper.toNewUser(user));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(location).body(UserMapper.toUserResponseDTO(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        User user = getUserByIdUseCase.execute(id);
        return ResponseEntity.ok(UserMapper.toUserResponseDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser( @PathVariable UUID id, @RequestBody UserUpdateRequestDTO user) {
        User updatedUser = updateUserUseCase.execute(UserMapper.toExistingUser(id, user));
        return ResponseEntity.ok(UserMapper.toUserResponseDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
