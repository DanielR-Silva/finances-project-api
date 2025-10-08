package finances.api.domain.model;

import java.util.UUID;

public class User {
    private final UUID id;
    private final String name;
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

    public void changePassword(String newPassword) {
        if (newPassword == null || newPassword.isBlank()) throw new IllegalArgumentException("New password cannot be null or blank");
        this.password = newPassword;
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

