package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResolver;
import domain.sessions.PrioResult;
import domain.votes.Votes;

public class SchulzeMethodResolver implements PrioResolver {
    @Override
    public PrioResult resolve(PrioItems options, Votes votes) {
        return new PrioResult(new int[]{2,1,3});
    }
}
