package domain;

public interface PrioSessionRepo {
    PrioSession findById(String sessionId) throws UnknownAggregateRootException;

    void update(PrioSession session);
}
