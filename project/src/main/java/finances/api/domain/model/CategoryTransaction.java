package finances.api.domain.model;

import java.util.UUID;

public class CategoryTransaction {

    private final UUID id;

    private final String value;

    public CategoryTransaction(UUID id, String value) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.value = value;
    }

    public UUID getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
