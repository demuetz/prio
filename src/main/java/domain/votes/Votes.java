package domain.votes;

import domain.Participant;

import java.util.*;

public class Votes implements Iterable<Vote> {

    private Map<Participant, Vote> votes = new HashMap<>();


    public static Votes fromList(List<Vote> votes){

        Votes newInstance = Votes.empty();

        for (Vote v : votes){
            if (newInstance.containsVoteFrom(v.getParticipant()))
                throw new IllegalArgumentException("Don't know how to consolidate multiple votes from participant " + v.getParticipant());

            newInstance.add(v);
        }
        return newInstance;
    }


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

    public boolean containsVoteFrom(Participant... participantsToCheck) {

        return votes.keySet().containsAll(Arrays.asList(participantsToCheck));

    }

    @Override
    public String toString() {
        if (votes.isEmpty()) return "Votes{empty}";

        String[] first5Names = votes.keySet().stream().limit(5).map(Participant::getName).toArray(String[]::new);

        return "Votes{" + String.join(", ", first5Names) + "}";
    }
}
