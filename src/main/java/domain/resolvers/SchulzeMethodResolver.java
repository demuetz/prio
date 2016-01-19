package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResolver;
import domain.sessions.Ranking;
import domain.votes.Votes;

import java.util.Map;

// https://en.wikipedia.org/wiki/Schulze_method
public class SchulzeMethodResolver implements PrioResolver {

    @Override
    public Ranking resolve(PrioItems options, Votes votes) {

        Map<Integer, Integer> scores =
                PairwisePrefs.from(votes)
                        .calculateStrongestPathWeights()
                        .calculateScores();

        return Ranking.fromItemsVotesAndScores(options, votes, scores);
    }
}