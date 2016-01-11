package domain.resolvers;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import java.util.Arrays;

import static domain.resolvers.PairwisePref.pwp;
import static org.junit.Assert.assertThat;

public class PairwisePrefCalculatorTest {

    private PairwisePrefCalculator sut = new PairwisePrefCalculator();

    @Test
    public void prefsMatchForSingleRankingInput() throws Exception {

        int[][] input = new int[][]{{30,20,10}};

        PairwisePref[] result = sut.resolve(input);

        assertThat(result, matches(pwp(30,20,1), pwp(30,10,1), pwp(20,10,1)));
    }

    @Test
    public void prefsMatchForTwoRankingInputs() throws Exception {

        int[][] input = new int[][]{{30,20,10}, {20,30,10}};

        PairwisePref[] result = sut.resolve(input);

        assertThat(result, matches(pwp(30,20,1), pwp(30,10,2), pwp(20,30,1), pwp(20,10,2)));
    }



    public static Matcher<PairwisePref[]> matches(PairwisePref... expected){
        return new TypeSafeMatcher<PairwisePref[]>() {
            @Override
            protected boolean matchesSafely(PairwisePref[] actual) {
                return Arrays.equals(actual, expected);
            }

            @Override
            public void describeTo(Description description) {

                description.appendText("exactly these prefs: " + Arrays.toString(expected));
            }
        };
    }
}
