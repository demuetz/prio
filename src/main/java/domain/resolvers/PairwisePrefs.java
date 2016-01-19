package domain.resolvers;

import domain.votes.Vote;
import domain.votes.Votes;
import javafx.util.Pair;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PairwisePrefs {
    private final SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph;

    public static PairwisePrefs from(Votes votes) {
        SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph =
                new SimpleDirectedWeightedGraph<>(PrioWeightedEdge.class);

        for (Vote v : votes){

            int currentOptionIndex = 0;

            int[] ranking = v.getRanking();

            addNonExistingVertices(prefsGraph, ranking);

            for (int winner : ranking){

                for (int i = currentOptionIndex+1; i < ranking.length; i++) {

                    int loser = ranking[i];

                    if (!prefsGraph.containsEdge(winner, loser)) {
                        prefsGraph.addEdge(winner, loser);
                    } else {
                        incrementEdgeWeight(prefsGraph, winner, loser);
                    }
                }
                currentOptionIndex++;
            }
        }

        return new PairwisePrefs(prefsGraph);
    }

    private static void addNonExistingVertices(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph, int[] ranking) {
        for (int option :ranking){
            prefsGraph.addVertex(option);
        }
    }

    private static void incrementEdgeWeight(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph, int winner, int loser) {
        PrioWeightedEdge e = prefsGraph.getEdge(winner, loser);
        prefsGraph.setEdgeWeight(e, prefsGraph.getEdgeWeight(e) + 1.0);
    }

    private PairwisePrefs(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph) {

        this.prefsGraph = prefsGraph;
    }

    private int weightOf(int winner, int loser) {
        PrioWeightedEdge edge = prefsGraph.getEdge(winner, loser);

        if (edge == null) return 0;

        return (int) prefsGraph.getEdgeWeight(edge);
    }

    // Variant of Floyd-Warshall Algorithm
    // See https://en.wikipedia.org/wiki/Schulze_method#Implementation
    public StrongestPathWeights calculateStrongestPathWeights() {
        Set<Integer> options = prefsGraph.vertexSet();

        Map<Pair<Integer, Integer>, Integer> strongestPathWeights = new HashMap<>();

        for (int option1 : options){
            for (int option2 : options){
                if (option1 != option2){
                    Pair<Integer, Integer> pair = new Pair<>(option1, option2);

                    int e1_2 = weightOf(option1, option2);
                    int e2_1 = weightOf(option2, option1);

                    int w = e1_2 > e2_1 ? e1_2 : 0;

                    strongestPathWeights.put(pair, w);
                }
            }
        }

        for (int i : options){
            for (int j : options){
                if (i != j){
                    options.stream().filter(k -> i != k && j != k).forEach(k -> {
                        Pair<Integer, Integer> pair = new Pair<>(j, k);
                        int jk = strongestPathWeights.get(pair);
                        int ji = strongestPathWeights.get(new Pair<>(j, i));
                        int ik = strongestPathWeights.get(new Pair<>(i, k));

                        int strength = Math.max(jk, Math.min(ji, ik));
                        strongestPathWeights.put(pair, strength);
                    });
                }
            }
        }
        return new StrongestPathWeights(strongestPathWeights);
    }
}
