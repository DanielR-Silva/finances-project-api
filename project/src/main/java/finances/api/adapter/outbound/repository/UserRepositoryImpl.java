package finances.api.adapter.outbound.repository;

import finances.api.domain.exceptions.DataPersistenceException;
import finances.api.domain.exceptions.EntityNotFoundDomainException;
import finances.api.domain.model.User;
import finances.api.domain.ports.output.UserRepositoryPort;
import finances.api.adapter.outbound.repository.mapper.UserMapper;
import finances.api.infrastructure.database.entity.UserEntity;
import finances.api.infrastructure.database.repository.UserRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryPort {

    private final UserRepositoryJpa repository;
    private final UserMapper mapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);

    @Override
    public boolean existsById(UUID id) {
        try {
            LOGGER.info("Executing exists user by id: {}", id);
            return repository.existsById(id);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing exists by id for UserId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean existsByEmail(String email) {
        try {
            LOGGER.info("Executing exists user by email: {}", email);
            return repository.existsByEmail(email);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing exists by email for UserEmail: {}", email, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        try {
            LOGGER.info("Executing find user by id: {}", id);
            return repository.findById(id).map(mapper::toDomain);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing find user by id for UserId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            LOGGER.info("Executing find all users");
            return repository.findAll().stream()
                    .map(mapper::toDomain)
                    .toList();
        }catch (DataAccessException e) {
            LOGGER.error("Error executing find all users for All Users", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public User save(User user) {
        try {
            UserEntity userEntity = mapper.toEntity(user);
            LOGGER.debug("UserEntity to be saved: {}", userEntity);
            LOGGER.info("Executing save user: {}", user);
            return mapper.toDomain(repository.save(userEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing save user", e);
            throw new DataPersistenceException(e.getMessage(), e);
        }

    }

    @Override
    public User update(User updatedUser) {
        try {
            LOGGER.info("Verifying user for UserID: {}", updatedUser.getId());
            UserEntity userEntity = repository.findById(updatedUser.getId())
                    .orElseThrow(
                            () -> new EntityNotFoundDomainException("UserEntity not found for id " + updatedUser.getId())
                    );
            LOGGER.debug("User to update: {}", updatedUser);
            User currentUser = mapper.toDomain(userEntity);
            currentUser.applyChangesFrom(updatedUser);
            UserEntity updatedUserEntity = mapper.toEntity(currentUser);
            LOGGER.info("Executing update user");
            return mapper.toDomain(repository.save(updatedUserEntity));
        }catch (DataAccessException e) {
            LOGGER.error("Error executing update user for UserId: {}", updatedUser.getId(), e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            LOGGER.info("Executing delete user by id: {}", id);
            repository.deleteById(id);
        }catch (DataAccessException e) {
            LOGGER.error("Error executing delete user by id for UserId: {}", id, e);
            throw new DataPersistenceException(e.getMessage(), e);
        }
    }
}