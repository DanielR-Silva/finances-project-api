package finances.api.adapter.inbound.controller;

import finances.api.domain.model.User;
import finances.api.domain.ports.inbound.*;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;
import finances.api.shared.mapper.UserMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
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

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = getAllUsersUseCase.execute();
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                users.stream()
                .map(UserMapper::toUserResponseDTO)
                .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser( @PathVariable UUID id, @RequestBody UserUpdateRequestDTO user) {
        User updatedUser = updateUserUseCase.execute(UserMapper.toUpdatedUser(id, user));
        return ResponseEntity.ok(UserMapper.toUserResponseDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        deleteUserUseCase.execute(id);
        return ResponseEntity.noContent().build();
    }
}
