package finances.api.adapter.outbound.repository;

import finances.api.domain.model.User;
import finances.api.domain.ports.outbound.UserRepositoryPort;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.UserRepositoryJpa;
import finances.api.shared.mapper.UserMapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public Optional<User> findById(UUID id) {
        return repository.findById(id).map(UserMapper::toExistingUser);
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = UserMapper.toCreateUserEntity(user);
        UserEntity savedUser = repository.save(userEntity);
        return UserMapper.toExistingUser(savedUser);
    }

    @Override
    public User update(User updatedUser) {
        UserEntity userEntity = repository.findById(updatedUser.getId())
                .orElseThrow(
                        () -> new EntityNotFoundException("UserEntity not found for id " + updatedUser.getId())
                );
        userEntity.setName(updatedUser.getName());
        userEntity.setEmail(updatedUser.getEmail());
        userEntity.setPassword(updatedUser.getPassword());
        return UserMapper.toExistingUser(repository.save(userEntity));
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}