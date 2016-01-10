package domain.sessions;

import domain.UnknownAggregateRootException;

import java.util.List;

public interface PrioSessionRepo {
    PrioSession findById(String sessionId) throws UnknownAggregateRootException;

    void update(PrioSession session);

    List<PrioSession> getAll();

    void create(PrioSession s);
}
