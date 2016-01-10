package domain;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Matchers.any;

public class PrioSessionTest {

    @Test
    public void keepsVotesFromNewVoters() throws Exception {

        PrioSession sut = newSessionWithItems("Bli", "Bla", "Blubb");

        castVoteFromParticipant(sut, "user 1", "user 2");

        PrioResolver resolver = mock(PrioResolver.class);

        sut.prioritize(resolver);

        //ToDo: verify user 2
        verify(resolver).resolve(any(PrioItems.class), argThat(containsVotesFrom("user 1")));
    }

    private void castVoteFromParticipant(PrioSession sut, String... names) {

        for (String name : names) {

            Vote vote = new Vote(Participant.withName(name), VotedOptions.withIds(1, 2));

            sut.cast(vote);
        }
    }

    private Matcher<Vote[]> containsVotesFrom(String participantName) {
        return new TypeSafeMatcher<Vote[]>() {
            public boolean matchesSafely(Vote[] votes) {

                for (Vote v : votes)
                    if(v.wasCastBy(Participant.withName(participantName)))
                        return true;
                return false;

            }
            public void describeTo(Description description) {
                description.appendText("a Vote cast by " + participantName);
            }
        };
    }

    private PrioSession newSessionWithItems(String... texts) {
        return PrioSession.newSession().withItemsFromTexts(texts).build();
    }
}
