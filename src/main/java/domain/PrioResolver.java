package domain;

public interface PrioResolver {
    PrioItems resolve(PrioItems options, Vote[] votes);
}
