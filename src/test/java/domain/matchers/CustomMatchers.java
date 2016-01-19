package domain.matchers;

import domain.Participant;
import domain.sessions.RankedItem;
import domain.sessions.Ranking;
import domain.votes.Votes;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public static Matcher<Ranking> hasRanking(Integer... expectedRanking){
        return new TypeSafeMatcher<Ranking>() {
            @Override
            protected boolean matchesSafely(Ranking ranking) {

                List<Integer> idsInOrder = new ArrayList<>();

                for (RankedItem item : ranking){
                    idsInOrder.add(item.getId());
                }

                return Arrays.equals(idsInOrder.toArray(new Integer[idsInOrder.size()]), expectedRanking);
            }

            @Override
            public void describeTo(Description description) {

                description.appendText("exactly this ranking: " + Arrays.toString(expectedRanking));
            }
        };
    }
}
