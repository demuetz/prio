package domain.votes;

import domain.resolvers.SchulzeMethodResolver;
import domain.sessions.Ranking;
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

        session.accept(vote);

        repo.update(session);
    }

    public Ranking resolvePrioritiesForSession(String sessionId) throws UnknownAggregateRootException {

        PrioSession session = repo.findById(sessionId);

        return session.prioritize(new SchulzeMethodResolver());
    }
}
