package domain.votes;

import org.junit.Before;
import org.junit.Test;

import static domain.matchers.CustomMatchers.containsVotesFrom;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

public class VotesTest {

    private Votes sut;

    @Before
    public void setUp() throws Exception {

        sut = Votes.empty();
    }

    @Test
    public void addsVote() throws Exception {

        Vote vote = castVote("user 1", 1, 2);

        assertThat(sut.contains(vote), is(true));
    }

    private Vote castVote(String participantName, int... optionIds) {
        Vote vote = Vote.fromParticipantNameWithOptions(participantName, optionIds);

        this.sut.add(vote);

        return vote;
    }

    @Test
    public void replacesVoteFromExistingParticipant() throws Exception {

        Vote previousVote = castVote("user 1", 1, 2);
        Vote currentVote = castVote("user 1", 1, 2, 2);

        assertThat("new vote from same participant is kept", sut.contains(currentVote), is(true));
        assertThat("old vote from same participant is discarded", sut.contains(previousVote), is(false));
    }

    @Test
    public void tracksParticipants() throws Exception {

        castVote("user 1", 1, 2);
        castVote("user 2", 1, 2);

        assertThat(sut, containsVotesFrom("user 1", "user 2"));
        assertThat(sut, not(containsVotesFrom("user 3", "user 4")));
    }
}
