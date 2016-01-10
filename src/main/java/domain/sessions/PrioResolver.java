package domain.sessions;

import domain.votes.Vote;

public interface PrioResolver {
    PrioItems resolve(PrioItems options, Vote[] votes);
}
