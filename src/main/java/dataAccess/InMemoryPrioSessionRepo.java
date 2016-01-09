package dataAccess;

import api.representations.Option;
import api.representations.PrioSession;
import com.google.common.base.Optional;

import java.util.*;

public class InMemoryPrioSessionRepo {

    private Map<String, PrioSession> sessions = new HashMap<String, PrioSession>();

    public InMemoryPrioSessionRepo() {

        for (PrioSession s:initialSessions()) {

            sessions.put(s.getKey(), s);
        }
    }

    private static List<PrioSession> initialSessions(){
        ArrayList<PrioSession> res = new ArrayList<PrioSession>();

        res.add(PrioSession.withKeyAndOptions("abc", createOptions("Bli", "Bla", "Blubb")));
        res.add(PrioSession.withKeyAndOptions("def", createOptions("Affe", "Zebra", "Dackel")));

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


    public void add(PrioSession session){
        sessions.put(session.getKey(), session);
    }

    public void update(PrioSession session){
        sessions.replace(session.getKey(), session);
    }


    public Optional<PrioSession> findByKey(String key){
        if (sessions.containsKey(key))
            return Optional.of(sessions.get(key));
        return Optional.absent();
    }

    public List<PrioSession> getAll() {
        return new ArrayList<PrioSession>(sessions.values());
    }
}
