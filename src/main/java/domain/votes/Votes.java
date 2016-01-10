package domain.votes;

import domain.Participant;

import java.util.*;

public class Votes implements Iterable<Vote> {

    private Map<Participant, Vote> votes = new HashMap<>();


    @Override
    public Iterator<Vote> iterator() {
        return votes.values().iterator();
    }

    public static Votes empty() {
        return new Votes();
    }

    public void add(Vote vote) {
        votes.put(vote.getParticipant(), vote);
    }

    public boolean contains(Vote vote) {
        return votes.values().contains(vote);
    }
}
