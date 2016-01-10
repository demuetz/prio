package dataAccess;

import domain.sessions.PrioItems;
import domain.sessions.PrioSession;
import domain.sessions.PrioSessionRepo;
import domain.UnknownAggregateRootException;

import java.util.*;

public class InMemoryPrioSessionRepo implements PrioSessionRepo {


    private Map<String, PrioSession> sessions = new HashMap<String, PrioSession>();

    public InMemoryPrioSessionRepo() {

        for (PrioSession s : initialSessions()){
            sessions.put(s.getId(), s);
        }
    }

    private PrioSession[] initialSessions() {

        return new PrioSession[]{
            new PrioSession("abc", PrioItems.fromTexts("Bli", "Bla", "Blubb")),
            new PrioSession("def", PrioItems.fromTexts("Affe", "Zebra", "Dackel")),
        };
    }


    public PrioSession findById(String sessionId) throws UnknownAggregateRootException {

        if (!sessions.containsKey(sessionId))
            throw new UnknownAggregateRootException();

        return sessions.get(sessionId);
    }

    public void update(PrioSession session) {
        sessions.put(session.getId(), session);
    }

    public List<PrioSession> getAll() {
        return new ArrayList<>(sessions.values());
    }

    @Override
    public void create(PrioSession s) {
        sessions.put(s.getId(), s);
    }

}
