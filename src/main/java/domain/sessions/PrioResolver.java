package domain.sessions;

import domain.votes.Votes;

public interface PrioResolver {
    PrioItems resolve(PrioItems options, Votes votes);
}
