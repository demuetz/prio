package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResolver;
import domain.sessions.PrioResult;
import domain.votes.Votes;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SchulzeMethodResolver implements PrioResolver {

    @Override
    public PrioResult resolve(PrioItems options, Votes votes) {

        Map<Integer, Integer> scores =
                PairwisePrefs.from(votes)
                        .calculateStrongestPathWeights()
                        .calculateScores();

        return PrioResult.fromItemsVotesAndScores(options, votes, scores);
    }
}