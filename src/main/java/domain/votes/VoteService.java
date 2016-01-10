package domain.votes;

import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import domain.UnknownAggregateRootException;

public class VoteService {
    private final PrioSessionRepo repo;

    public VoteService(PrioSessionRepo repo) {

        this.repo = repo;
    }

    public void castVoteForSession(Vote vote, String sessionId) throws UnknownAggregateRootException {

        PrioSession session = repo.findById(sessionId);

        session.cast(vote);

        repo.update(session);
    }
}
