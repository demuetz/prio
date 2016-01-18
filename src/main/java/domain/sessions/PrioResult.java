package domain.sessions;

import domain.votes.Votes;

import java.util.*;
import java.util.stream.Collectors;

public class PrioResult {
    private final int[] ranking;
    private final int voteCount;

    public PrioResult(int voteCount, int[] ranking) {
        this.voteCount = voteCount;
        this.ranking = ranking;
    }

    public static PrioResult fromItemsVotesAndScores(PrioItems items, Votes votes, Map<Integer, Integer> scores) {

        //ToDo: ranking should be a list of ranked items with id, rank, text and score

        return new PrioResult(votes.getCount(), toArray(toRankedIds(scores)));
    }


    private static List<Integer> toRankedIds(Map<Integer, Integer> scores) {
        return scores.entrySet().stream()
                .sorted(Comparator.comparing(e -> e.getValue() * -1))
                .map(Map.Entry::getKey).collect(Collectors.toList());
    }

    private static int[] toArray(List<Integer> rankedIds) {
        int[] ranked = new int[rankedIds.size()];

        int i = 0;
        for (int id : rankedIds){
            ranked[i] = id;
            i++;
        }
        return ranked;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public int[] getRanking() {
        return ranking;
    }

    @Override
    public String toString() {
        return "PrioResult{" +
                "ranking=" + Arrays.toString(ranking) +
                '}';
    }
}
