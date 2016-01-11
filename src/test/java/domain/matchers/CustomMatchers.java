package domain.matchers;

import domain.Participant;
import domain.sessions.PrioResult;
import domain.votes.Votes;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.Arrays;

public class CustomMatchers {
    public static Matcher<Votes> containsVotesFrom(String... participantNames) {
        return new TypeSafeMatcher<Votes>() {
            public boolean matchesSafely(Votes votes) {

                Participant[] expectedParticipants = Arrays.stream(participantNames).map(Participant::withName).toArray(Participant[]::new);

                return votes.containsVoteFrom(expectedParticipants);

            }
            public void describeTo(Description description) {
                description.appendText("a Vote by each of " + String.join(", ", participantNames));
            }
        };
    }

    public static Matcher<PrioResult> hasRanking(int... expectedRanking){
        return new TypeSafeMatcher<PrioResult>() {
            @Override
            protected boolean matchesSafely(PrioResult prioResult) {
                return Arrays.equals(prioResult.getRanking(), expectedRanking);
            }

            @Override
            public void describeTo(Description description) {

                description.appendText("exactly this ranking: " + Arrays.toString(expectedRanking));
            }
        };
    }
}
