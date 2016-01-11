package domain.sessions;

import domain.votes.Votes;

public interface PrioResolver {
    PrioResult resolve(PrioItems options, Votes votes);
}
