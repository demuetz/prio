package domain.resolvers;

import domain.sessions.PrioItems;
import domain.sessions.PrioResolver;
import domain.sessions.PrioResult;
import domain.votes.Vote;
import domain.votes.Votes;
import javafx.util.Pair;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.*;
import java.util.stream.Collectors;

public class SchulzeMethodResolver implements PrioResolver {

    @Override
    public PrioResult resolve(PrioItems options, Votes votes) {

        SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> pwpGraph = calculatePairwisePrefs(options, votes);

        StrongestPathWeights strongestPathWeights = calculateStrongestPathWeights(options, pwpGraph);

        Map<Integer, Integer> scores = strongestPathWeights.calculateScores();

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

    private StrongestPathWeights calculateStrongestPathWeights(PrioItems options, SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> pwpGraph) {
        Map<Pair<Integer, Integer>, Integer> strongestPathWeights = new HashMap<>();

        for (int winner : options.getIds()){
            for (int loser : options.getIds()){
                if (winner != loser){
                    Pair<Integer, Integer> pair = new Pair<>(winner, loser);

                    int e1_2 = weight(pwpGraph, winner, loser);
                    int e2_1 = weight(pwpGraph, loser, winner);

                    int w = e1_2 > e2_1 ? e1_2 : 0;

                    strongestPathWeights.put(pair, w);
                }
            }
        }

        for (int i : options.getIds()){
            for (int j : options.getIds()){
                if (i != j){
                    for (int k : options.getIds()){
                        if (i != k && j != k){
                            Pair<Integer, Integer> pair = new Pair<>(j,k);
                            int jk = strongestPathWeights.get(pair);
                            int ji = strongestPathWeights.get(new Pair<>(j,i));
                            int ik = strongestPathWeights.get(new Pair<>(i,k));

                            int strength = Math.max(jk, Math.min(ji, ik));
                            strongestPathWeights.put(pair, strength);
                        }
                    }
                }
            }
        }
        return new StrongestPathWeights(strongestPathWeights);
    }

    private int weight(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> pwpGraph, int winner, int loser) {

        PrioWeightedEdge edge = pwpGraph.getEdge(winner, loser);

        if (edge == null) return 0;

        return (int) pwpGraph.getEdgeWeight(edge);
    }

    private SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> calculatePairwisePrefs(PrioItems options, Votes votes) {

        SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph =
                new SimpleDirectedWeightedGraph<>(PrioWeightedEdge.class);

        for (int optionId : options.getIds()){
            prefsGraph.addVertex(optionId);
        }

        for (Vote v : votes){

            int currentOptionIndex = 0;

            int[] ranking = v.getRanking();

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

        return prefsGraph;
    }

    private void incrementEdgeWeight(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> prefsGraph, int winner, int loser) {
        PrioWeightedEdge e = prefsGraph.getEdge(winner, loser);
        prefsGraph.setEdgeWeight(e, prefsGraph.getEdgeWeight(e) + 1.0);
    }
}