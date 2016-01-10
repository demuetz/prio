package domain.votes;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class VotesTest {

    @Test
    public void addsVote() throws Exception {

        Votes sut = Votes.empty();

        Vote vote = castVote(sut, "user 1", 1, 2);

        assertThat(sut.contains(vote), is(true));
    }

    private Vote castVote(Votes sut, String participantName, int... optionIds) {
        Vote vote = Vote.fromParticipantNameWithOptions(participantName, optionIds);

        sut.add(vote);

        return vote;
    }

    @Test
    public void replacesVoteFromExistingParticipant() throws Exception {

        Votes sut = Votes.empty();

        Vote previousVote = castVote(sut, "user 1", 1, 2);
        Vote currentVote = castVote(sut, "user 1", 1, 2, 2);

        assertThat("new vote from same participant is kept", sut.contains(currentVote), is(true));
        assertThat("old vote from same participant is discarded", sut.contains(previousVote), is(false));
    }
}
