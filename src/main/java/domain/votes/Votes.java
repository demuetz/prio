package domain.votes;

import domain.Participant;

import java.util.*;

public class Votes implements Iterable<Vote> {

    private Map<Participant, Vote> votes = new HashMap<>();


    @Override
    public Iterator<Vote> iterator() {
        return votes.values().iterator();
    }
}
