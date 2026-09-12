package finances.api.adapter.outbound.repository;

import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.User;
import finances.api.domain.ports.output.UserRepositoryPort;
import finances.api.adapter.outbound.repository.mapper.UserMapper;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPort {

    private final UserRepositoryJpa repository;
    private final UserMapper mapper;

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
        return repository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = mapper.toEntity(user);
        UserEntity savedUser = repository.save(userEntity);
        return mapper.toDomain(savedUser);
    }

    @Override
    public User update(User updatedUser) {
        UserEntity userEntity = repository.findById(updatedUser.getId())
                .orElseThrow(
                        () -> new EntityNotFoundDomainException("UserEntity not found for id " + updatedUser.getId())
                );
        User currentUser = mapper.toDomain(userEntity);
        currentUser.applyChangesFrom(updatedUser);
        userEntity.setName(currentUser.getName());
        userEntity.setEmail(currentUser.getEmail());
        userEntity.setPassword(currentUser.getPassword());
        return mapper.toDomain(repository.save(userEntity));
    }

    @Override
    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}