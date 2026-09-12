package finances.api.adapter.inbound.controller;

import finances.api.application.service.UserApplicationService;
import finances.api.adapter.inbound.controller.mapper.UserMapper;
import finances.api.domain.model.User;
import finances.api.shared.dto.request.UserRequestDTO;
import finances.api.shared.dto.request.UserUpdateRequestDTO;
import finances.api.shared.dto.response.UserResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final UserApplicationService userService;
    private final UserMapper mapper;

    @PostMapping()
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) {
        LOGGER.info("Creating user");
        User createdUser = userService.create(mapper.toNewUser(user));
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
        LOGGER.info("User created with id: {}", createdUser.getId());
        LOGGER.debug("User: {}", createdUser);
        return ResponseEntity.created(location).body(mapper.toUserResponseDTO(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable UUID id) {
        LOGGER.info("Getting user by id: {}", id);
        User user = userService.getById(id);
        LOGGER.info("User found");
        LOGGER.debug("User: {}", user);
        return ResponseEntity.ok(mapper.toUserResponseDTO(user));
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        LOGGER.info("Getting all users");
        List<User> users = userService.listAll();
        LOGGER.info("Users count: {}", users.size());
        LOGGER.debug("Users: {}", users);
        if (users.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(
                users.stream()
                .map(mapper::toUserResponseDTO)
                .toList()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable UUID id, @Valid @RequestBody UserUpdateRequestDTO user) {
        LOGGER.info("Updating user with id: {}", id);
        User updatedUser = userService.update(mapper.toUser(id, user));
        LOGGER.info("User updated");
        LOGGER.debug("User updated: {}", updatedUser);
        return ResponseEntity.ok(mapper.toUserResponseDTO(updatedUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        LOGGER.info("Deleting user with id: {}", id);
        userService.delete(id);
        LOGGER.info("User deleted");
        return ResponseEntity.noContent().build();
    }
}
