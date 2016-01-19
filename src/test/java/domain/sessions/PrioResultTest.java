package domain.sessions;

import domain.votes.Vote;
import domain.votes.Votes;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PrioResultTest {
    @Test
    public void containsNumberOfVotes() throws Exception {

        PrioItems items = PrioItems.fromTexts("A", "B", "C");

        PrioResult sut = PrioResult.fromItemsVotesAndScores(items, votes(15), someScores(items));

        assertThat(sut.getVoteCount(), is(15));
    }

    @Test
    public void containsRankedItems() throws Exception {

        PrioItems items = PrioItems.fromTexts("A", "B", "C");

        Map<Integer, Integer> scores = scores(0, 20, 10);

        PrioResult sut = PrioResult.fromItemsVotesAndScores(items, votes(15), scores);

        assertThat(sut, contains(
                new RankedItem(2, "B", 1, 20),
                new RankedItem(3, "C", 2, 10),
                new RankedItem(1, "A", 3, 0)));
    }

    @Test
    public void assignsSameRankToEqualScores() throws Exception {

        PrioItems items = PrioItems.fromTexts("A", "B", "C", "D");

        Map<Integer, Integer> scores = scores(0, 10, 10, 20);

        PrioResult sut = PrioResult.fromItemsVotesAndScores(items, votes(15), scores);

        assertThat(sut, contains(
                new RankedItem(4, "D", 1, 20),
                new RankedItem(2, "B", 2, 10),
                new RankedItem(3, "C", 2, 10),
                new RankedItem(1, "A", 4, 0)));
    }

    private Map<Integer, Integer> someScores(PrioItems items) {

        List<Integer> scores = items.stream().map(i -> (int)(Math.random()*100)).collect(Collectors.toList());

        return scores(new int[scores.size()]);
    }

    private Map<Integer, Integer> scores(int... scoreValues) {
        Map<Integer, Integer> scores = new HashMap<>();

        int id = 1;

        for (int value : scoreValues){
            scores.put(id++, value);
        }
        return scores;
    }

    private Votes votes(int count) {
        Votes v = Votes.empty();

        for (int i = 0; i < count; i++){
            v.add(Vote.fromParticipantNameWithOptions("participant " + i));
        }

        return v;
    }
}