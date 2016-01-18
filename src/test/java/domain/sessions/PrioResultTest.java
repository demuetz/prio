package domain.sessions;

import domain.votes.Vote;
import domain.votes.Votes;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrioResultTest {
    @Test
    public void containsNumberOfVotes() throws Exception {

        PrioItems items = PrioItems.fromTexts("A", "B", "C");

        java.util.Map<Integer, Integer> scores = new HashMap<>();
        scores.put(1, 15);
        scores.put(2, 25);
        scores.put(3, 0);

        PrioResult sut = PrioResult.fromItemsVotesAndScores(items, votes(3), scores);

        assertThat(sut.getVoteCount(), is(3));
    }

    private Votes votes(int count) {
        Votes v = Votes.empty();

        for (int i = 0; i < count; i++){
            v.add(Vote.fromParticipantNameWithOptions("participant " + i));
        }

        return v;
    }
}
