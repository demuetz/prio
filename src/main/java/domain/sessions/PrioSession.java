package domain.sessions;

import domain.votes.Vote;
import domain.votes.Votes;

import java.util.UUID;

public class PrioSession {

    private String id;
    private PrioItems items;
    private Votes votes = Votes.empty();

    public static PrioSession create() {

        return new PrioSession(UUID.randomUUID().toString(), PrioItems.empty());
    }

    public PrioSession(String id, PrioItems items) {

        this.id = id;
        this.items = items;
    }


    public void cast(Vote vote) {
        votes.add(vote);
    }

    public String getId() {
        return id;
    }

    public PrioItems getItems() {
        return items;
    }



    public void addItems(PrioItems newItems) {
        items = items.join(newItems);
    }

    public static PrioSessionBuilder newSession() {
        return new PrioSessionBuilder().newSession();
    }

    public void prioritize(PrioResolver resolver) {
        resolver.resolve(items, votes);
    }
}
