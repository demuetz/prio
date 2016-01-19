package domain.sessions;

import domain.votes.Vote;
import domain.votes.Votes;

public class PrioSession {

    private String id;
    private PrioItems items;
    private Votes votes = Votes.empty();

    public static PrioSessionBuilder newSession() {
        return new PrioSessionBuilder().newSession();
    }

    public PrioSession(String id, PrioItems items) {

        this.id = id;
        this.items = items;
    }


    public void addItems(PrioItems newItems) {
        items = items.join(newItems);
    }

    public Ranking prioritize(PrioResolver resolver) {
        return resolver.resolve(items, votes);
    }

    public void accept(Vote vote) {

        //ToDo: Assert that vote matches options
        votes.add(vote);
    }


    public String getId() {
        return id;
    }

    public PrioItems getItems() {
        return items;
    }
}