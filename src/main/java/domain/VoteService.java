package domain;

public class VoteService {
    private final PrioSessionRepo repo;

    public VoteService(PrioSessionRepo repo) {

        this.repo = repo;
    }

    public void castVoteForSession(Vote vote, String sessionId) {

        PrioSession session = repo.findById(sessionId);

        session.cast(vote);

        repo.update(session);
    }
}
