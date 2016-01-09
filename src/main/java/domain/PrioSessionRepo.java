package domain;

public interface PrioSessionRepo {
    PrioSession findById(String sessionId);

    Void update(PrioSession session);
}
