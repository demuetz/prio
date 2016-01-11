package domain.resolvers;

import java.util.HashMap;
import java.util.Map;

import static domain.resolvers.PairwisePref.pwp;

public class PairwisePrefCalculator {
    public PairwisePref[] resolve(int[][] input) {

        Map<Pair, Integer> result = new HashMap<>();

        for (int[] ranking : input){

            int currentOptionIndex = 0;

            for(int option : ranking){

                for (int i = currentOptionIndex+1; i < ranking.length; i++) {

                    Pair p = Pair.pair(option, ranking[i]);

                    int newCount = result.containsKey(p) ? result.get(p)+1 : 1;

                    result.put(p, newCount);
                }
                currentOptionIndex++;
            }
        }

        //ToDo: Sort to make results predictable
        return result.entrySet().stream()
                .map(this::toPairwisePref)
                //.sorted(Comparator.comparing(x -> x.pair.winner).thenComparingInt())
                .toArray(PairwisePref[]::new);
    }

    private PairwisePref toPairwisePref(Map.Entry<Pair, Integer> x) {
        return pwp(x.getKey().winner, x.getKey().loser, x.getValue());
    }
}
