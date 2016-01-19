package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResolver;
import domain.sessions.PrioResult;
import domain.votes.Votes;

import java.util.Map;

// https://en.wikipedia.org/wiki/Schulze_method
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