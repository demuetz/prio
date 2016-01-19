package domain.sessions;

import domain.votes.Votes;

import java.util.*;
import java.util.stream.Collectors;

public class Ranking implements Iterable<RankedItem>{
    private final int voteCount;

    private final List<RankedItem> rankedItems;

    public Ranking(int voteCount, RankedItem[] rankedItems) {
        this.voteCount = voteCount;
        this.rankedItems = Arrays.asList(rankedItems);
    }

    public static Ranking fromItemsVotesAndScores(PrioItems items, Votes votes, Map<Integer, Integer> scores) {

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

        return new Ranking(votes.getCount(), rankedItems);
    }

    public int getVoteCount() {
        return voteCount;
    }

    @Override
    public String toString() {
        return "PrioResult{" +
                "rankedItems=" + Arrays.toString(rankedItems.toArray()) +
                '}';
    }

    @Override
    public Iterator<RankedItem> iterator() {
        return rankedItems.iterator();
    }
}
