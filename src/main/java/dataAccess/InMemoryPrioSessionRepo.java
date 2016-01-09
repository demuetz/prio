package dataAccess;

import api.representations.Option;
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

            doofSsessions.put(s.getKey(), s);
        }
    }

    private PrioSession[] initialSessions() {

        return new PrioSession[]{
            new PrioSession("abc", PrioItems.withText("Bli", "Bla", "Blubb")),
            new PrioSession("def", PrioItems.withText("Affe", "Zebra", "Dackel")),
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















    private Map<String, PrioSessionDto> doofSsessions = new HashMap<String, PrioSessionDto>();



    private static List<PrioSessionDto> initialDoofSessions(){
        ArrayList<PrioSessionDto> res = new ArrayList<PrioSessionDto>();

        res.add(PrioSessionDto.withKeyAndOptions("abc", createOptions("Bli", "Bla", "Blubb")));
        res.add(PrioSessionDto.withKeyAndOptions("def", createOptions("Affe", "Zebra", "Dackel")));

        return res;
    }

    private static List<Option> createOptions(String... texts) {

        List<Option> options = new ArrayList<Option>();

        int id = 0;

        for (String t: texts) {
            options.add(Option.withIdAndText(id++, t));
        }

        return options;
    }


    public Optional<PrioSessionDto> findByKey(String key){
        if (doofSsessions.containsKey(key))
            return Optional.of(doofSsessions.get(key));
        return Optional.absent();
    }

    public List<PrioSessionDto> getAll() {
        return new ArrayList<PrioSessionDto>(doofSsessions.values());
    }


}
