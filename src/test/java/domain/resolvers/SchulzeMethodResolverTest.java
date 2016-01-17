package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResult;
import domain.votes.Vote;
import domain.votes.Votes;
import org.junit.Test;

import java.util.*;

import static domain.matchers.CustomMatchers.hasRanking;
import static org.junit.Assert.assertThat;

public class SchulzeMethodResolverTest {

    private SchulzeMethodResolver sut = new SchulzeMethodResolver();


    @Test
    public void onlyOneVoteWithAllOptionsYieldsResultMatchingVote() throws Exception {

        PrioItems options = PrioItems.fromTexts("one", "two", "three");
        Votes singleVote = singleVote("bernie", 2, 1, 3);

        PrioResult r = sut.resolve(options, singleVote);

        assertThat(r , hasRanking(2, 1, 3));
    }

    @Test
    public void wikipediaExampleGivesExpectedResult() throws Exception {

        PrioItems options = PrioItems.fromTexts("A", "B", "C", "D", "E");

        PrioResult r = sut.resolve(options, wikipediaExampleVotes());

        assertThat(r , hasRanking(5, 1, 3, 2, 4)); // EACBD
    }

    private Votes wikipediaExampleVotes() {
        Votes votes = Votes.empty();

        addWikipediaExampleVotes(votes, 5, "ACBED");
        addWikipediaExampleVotes(votes, 5, "ADECB");
        addWikipediaExampleVotes(votes, 8, "BEDAC");
        addWikipediaExampleVotes(votes, 3, "CABED");
        addWikipediaExampleVotes(votes, 7, "CAEBD");
        addWikipediaExampleVotes(votes, 2, "CBADE");
        addWikipediaExampleVotes(votes, 7, "DCEBA");
        addWikipediaExampleVotes(votes, 8, "EBADC");
        return votes;
    }


    private void addWikipediaExampleVotes(Votes votes, int count, String ranking) {

        for (int i = 0; i < count; i++){
            String voterName = ranking + "_" + (i+1);
            int[] rankingIds = toIdRanking(ranking);
            votes.add(Vote.fromParticipantNameWithOptions(voterName, rankingIds));
        }
    }

    private int[] toIdRanking(String ranking) {

        int[] result = new int[ranking.length()];

        for (int i = 0; i < ranking.length(); i++){
            result[i] = "ABCDE".indexOf(ranking.charAt(i)) + 1;
        }

        return result;
    }

    private Votes singleVote(String participantName, int... votedOptions) {
        return Votes.fromList(
                Collections.singletonList(
                        Vote.fromParticipantNameWithOptions(participantName, votedOptions)));
    }
}
