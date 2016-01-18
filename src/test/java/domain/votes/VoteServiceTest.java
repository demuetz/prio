package domain.votes;

import domain.UnknownAggregateRootException;
import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class VoteServiceTest {

    private String sessionId;
    private PrioSession session;
    private PrioSessionRepo repo;

    @Before
    public void setUp() throws Exception {

        sessionId = "some session id";

        session = sessionWithId(sessionId);

        repo = repoContainingSession(session);
    }

    @Test
    public void acceptableVoteIsCastOnSession() throws Exception {

        Vote vote = createVote();

        castVoteOnService(vote);

        InOrder inOrder = inOrder(repo, session);

        inOrder.verify(session).accept(vote);
        inOrder.verify(repo).update(session);
    }

    private void castVoteOnService(Vote vote) throws UnknownAggregateRootException {
        new VoteService(repo).castVoteForSession(vote, sessionId);
    }

    private Vote createVote() {

        return Vote.fromParticipantNameWithOptions("Hansi", 1,2,3);
    }

    private PrioSession sessionWithId(String sessionId) {
        PrioSession session = mock(PrioSession.class);
        when(session.getId()).thenReturn(sessionId);
        return session;
    }

    private PrioSessionRepo repoContainingSession(PrioSession session) throws UnknownAggregateRootException {
        PrioSessionRepo repo = mock(PrioSessionRepo.class);

        when(repo.findById(session.getId())).thenReturn(session);

        return repo;
    }
}
