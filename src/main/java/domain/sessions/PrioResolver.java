package domain.sessions;

import domain.votes.Votes;

public interface PrioResolver {
    Ranking resolve(PrioItems options, Votes votes);
}
