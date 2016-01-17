package domain.resolvers;

import javafx.util.Pair;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StrongestPathWeights {
    private final Map<Pair<Integer, Integer>, Integer> strongestPathWeights;

    public StrongestPathWeights(Map<Pair<Integer, Integer>, Integer> strongestPathWeights) {

        this.strongestPathWeights = strongestPathWeights;
    }


    private Map<Pair<Integer, Integer>, Integer> getPairwiseWinners() {
        List<Integer> ids = optionIds();

        Map<Pair<Integer, Integer>, Integer> pairwiseWinners = new HashMap<>();

        for (int winner : ids){
            for (int loser : ids){
                if (winner != loser){
                    Pair<Integer, Integer> o1Wins = new Pair<>(winner, loser);
                    Pair<Integer, Integer> o2Wins = new Pair<>(loser, winner);

                    if (strongestPathWeights.get(o1Wins) > strongestPathWeights.get(o2Wins)){
                        pairwiseWinners.put(o1Wins, strongestPathWeights.get(o1Wins));
                    } else {
                        pairwiseWinners.put(o2Wins, strongestPathWeights.get(o2Wins));
                    }
                }
            }
        }
        return pairwiseWinners;
    }

    private List<Integer> optionIds() {
        return strongestPathWeights.keySet().stream().map(Pair::getKey).collect(Collectors.toList());
    }

    public Map<Integer, Integer> calculateScores() {

        Map<Pair<Integer, Integer>, Integer> pairwiseWinners = getPairwiseWinners();

        Map<Integer, Integer> scores = new HashMap<>();

        for (Pair<Integer, Integer> winner : pairwiseWinners.keySet()){

            int previousScore = scores.containsKey(winner.getKey()) ? scores.get(winner.getKey()) : 0;

            scores.put(winner.getKey(), pairwiseWinners.get(winner) + previousScore);
        }

        optionIds().stream().filter(option -> !scores.containsKey(option))
                .forEach(option -> scores.put(option, 0));

        return scores;
    }
}
