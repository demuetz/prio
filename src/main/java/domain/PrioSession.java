package domain;

import java.util.UUID;

public class PrioSession {

    private String id;
    private PrioItems items;

    public PrioSession(String id, PrioItems items) {

        this.id = id;
        this.items = items;
    }

    public Void cast(Vote vote) {
        return null;
    }

    public String getId() {
        return id;
    }

    public PrioItems getItems() {
        return items;
    }

    public static PrioSession create() {

        return new PrioSession(UUID.randomUUID().toString(), PrioItems.empty());
    }

    public void addItems(PrioItems newItems) {
        items = items.join(newItems);
    }
}
