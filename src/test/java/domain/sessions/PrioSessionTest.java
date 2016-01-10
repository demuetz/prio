package domain.sessions;

import domain.Participant;
import domain.votes.Vote;
import domain.votes.VotedOptions;
import org.junit.Test;

import static domain.matchers.CustomMatchers.containsVotesFrom;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrioSessionTest {

    @Test
    public void keepsVotesFromNewVoters() throws Exception {

        PrioSession sut = newSessionWithItems("Bli", "Bla", "Blubb");

        castVoteFromParticipant(sut, "user 1", "user 2");

        PrioResolver resolver = mock(PrioResolver.class);

        sut.prioritize(resolver);

        verify(resolver).resolve(any(PrioItems.class), argThat(containsVotesFrom("user 1", "user 2")));
    }

    private void castVoteFromParticipant(PrioSession sut, String... names) {

        for (String name : names) {

            Vote vote = new Vote(Participant.withName(name), VotedOptions.withIds(1, 2));

            sut.accept(vote);
        }
    }

    private PrioSession newSessionWithItems(String... texts) {
        return PrioSession.newSession().withItemsFromTexts(texts).build();
    }
}
