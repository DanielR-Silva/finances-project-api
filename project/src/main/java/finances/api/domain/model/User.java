package finances.api.domain.model;

import finances.api.domain.ports.output.PasswordEncoderPort;

import java.util.UUID;

public class User {
    private final UUID id;
    private String name;
    private String email;
    private String password;

    public User(UUID id, String name, String email, String password) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name cannot be null or blank");
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email");

        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public static User create(String name, String email, String password) {
        return new User(UUID.randomUUID(), name, email, password);
    }

    public static User reconstitute(UUID id, String name, String email, String password) {
        return new User(id, name, email, password);
    }

    public void changeEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("Invalid email");
        this.email = email;
    }

    public void encryptPassword(PasswordEncoderPort passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public void applyChangesFrom(User updates) {
        if (updates.name != null && !updates.name.isBlank()) {
            this.name = updates.name;
        }
        if (updates.email != null && !updates.email.isBlank()) {
            changeEmail(updates.email);
        }
        if (updates.password != null && !updates.password.isBlank()) {
            this.password = updates.password;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

