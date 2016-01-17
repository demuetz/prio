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

        return new PrioResult(toArray(toRankedIds(scores)));
    }

    private List<Integer> toRankedIds(Map<Integer, Integer> scores) {
        return scores.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue() * -1))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private int[] toArray(List<Integer> rankedIds) {
        int[] ranked = new int[rankedIds.size()];

        int i = 0;
        for (int id : rankedIds){
            ranked[i] = id;
            i++;
        }
        return ranked;
    }
}