package domain.sessions;

import java.util.UUID;

public class PrioSessionBuilder {

    private String id;
    private PrioItems items = PrioItems.empty();

    public PrioSession build() {
        return new PrioSession(id, items);
    }

    public PrioSessionBuilder newSession() {
        id = UUID.randomUUID().toString();
        return this;
    }

    public PrioSessionBuilder withItemsFromTexts(String... texts) {
        items = PrioItems.fromTexts(texts);
        return this;
    }
}
