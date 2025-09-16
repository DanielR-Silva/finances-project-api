package finances.api.adapter.outbound.repository;

import finances.api.domain.model.User;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.UserRepositoryJpa;
import finances.api.shared.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPort {

    private final UserRepositoryJpa repository;

    @Override
    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(UserMapper::toUpdatedUser);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(UserMapper::toUpdatedUser)
                .toList();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toCreateUserEntity(user);
        UserEntity savedUser = repository.save(userEntity);
        return UserMapper.toUpdatedUser(savedUser);
    }

    @Override
    public User update(User updatedUser) {
        UserEntity userEntity = repository.findById(updatedUser.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("UserEntity not found for id " + updatedUser.getId())
                );
        if (!updatedUser.getName().isBlank()) userEntity.setName(updatedUser.getName());
        if (!updatedUser.getEmail().isBlank()) userEntity.setEmail(updatedUser.getEmail());
        if (!updatedUser.getPassword().isBlank()) userEntity.setPassword(updatedUser.getPassword());
        return UserMapper.toUpdatedUser(repository.save(userEntity));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}