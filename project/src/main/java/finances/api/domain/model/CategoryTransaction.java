package finances.api.domain.model;

import java.util.UUID;

public class CategoryTransaction {

    private final UUID id;

    private String value;

    public CategoryTransaction(UUID id, String value) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.value = value;
    }

    public static CategoryTransaction create(String value) {
        return new CategoryTransaction(UUID.randomUUID(), value);
    }

    public static CategoryTransaction reconstitute(UUID id, String value) {
        return new CategoryTransaction(id, value);
    }

    public void applyChangesFrom(CategoryTransaction updates) {
        if (updates.value != null && !updates.value.isBlank()) {
            this.value = updates.value;
        }
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
