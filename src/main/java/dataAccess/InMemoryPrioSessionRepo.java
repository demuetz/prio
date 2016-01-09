package dataAccess;

import api.representations.PrioItemDto;
import api.representations.PrioSessionDto;
import com.google.common.base.Optional;
import domain.PrioItems;
import domain.PrioSession;
import domain.PrioSessionRepo;
import domain.UnknownAggregateRootException;

import java.util.*;

public class InMemoryPrioSessionRepo implements PrioSessionRepo {


    private Map<String, PrioSession> sessions = new HashMap<String, PrioSession>();

    public InMemoryPrioSessionRepo() {

        for (PrioSession s : initialSessions()){
            sessions.put(s.getId(), s);
        }

        for (PrioSessionDto s: initialDoofSessions()) {

            doofSsessions.put(s.getId(), s);
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
        return new ArrayList<PrioSession>(sessions.values());
    }


    private Map<String, PrioSessionDto> doofSsessions = new HashMap<String, PrioSessionDto>();



    private static List<PrioSessionDto> initialDoofSessions(){
        ArrayList<PrioSessionDto> res = new ArrayList<PrioSessionDto>();

        res.add(PrioSessionDto.withKeyAndOptions("abc", createOptions("Bli", "Bla", "Blubb")));
        res.add(PrioSessionDto.withKeyAndOptions("def", createOptions("Affe", "Zebra", "Dackel")));

        return res;
    }

    private static List<PrioItemDto> createOptions(String... texts) {

        List<PrioItemDto> prioItemDtos = new ArrayList<PrioItemDto>();

        int id = 0;

        for (String t: texts) {
            prioItemDtos.add(PrioItemDto.withIdAndText(id++, t));
        }

        return prioItemDtos;
    }


    public Optional<PrioSessionDto> findByKey(String key){
        if (doofSsessions.containsKey(key))
            return Optional.of(doofSsessions.get(key));
        return Optional.absent();
    }
}
