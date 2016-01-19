package domain.sessions;

import domain.votes.Votes;

import java.util.*;
import java.util.stream.Collectors;

public class PrioResult implements Iterable<RankedItem>{
    private final int[] ranking;
    private final int voteCount;

    private final List<RankedItem> rankedItems;

    public PrioResult(int voteCount, int[] ranking, RankedItem[] rankedItems) {
        this.voteCount = voteCount;
        this.ranking = ranking;
        this.rankedItems = Arrays.asList(rankedItems);
    }

    public static PrioResult fromItemsVotesAndScores(PrioItems items, Votes votes, Map<Integer, Integer> scores) {

        RankedItem[] rankedItems = new RankedItem[items.size()];

        List<Map.Entry<Integer, Integer>> orderedScores = scores.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue() * -1)).collect(Collectors.toList());

        int previousScore = 0;
        int previousRank = 0;
        for (int i = 0; i < orderedScores.size(); i++){

            int score = orderedScores.get(i).getValue();
            int rank = score == previousScore ? previousRank : i+1;
            int id = orderedScores.get(i).getKey();

            rankedItems[i] = new RankedItem(id, items.textForId(id), rank, score);

            previousRank = rank;
            previousScore = score;
        }

        return new PrioResult(votes.getCount(), toArray(toRankedIds(scores)), rankedItems);
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

    @Override
    public Iterator<RankedItem> iterator() {
        return rankedItems.iterator();
    }
}
