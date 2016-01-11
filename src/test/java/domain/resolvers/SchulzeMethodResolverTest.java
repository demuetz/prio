package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResult;
import domain.votes.Vote;
import domain.votes.Votes;
import org.junit.Test;

import java.util.Collections;

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



    private Votes singleVote(String participantName, int... votedOptions) {
        return Votes.fromList(
                Collections.singletonList(
                        Vote.fromParticipantNameWithOptions(participantName, votedOptions)));
    }
}
