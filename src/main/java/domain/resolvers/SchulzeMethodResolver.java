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

        Map<Pair<Integer, Integer>, Integer> strongestPathWeights = calculateStrongestPathWeights(options, pwpGraph);

        Map<Pair<Integer, Integer>, Integer> pairwiseWinners = new HashMap<>();

        for (int option1 : options.getIds()){
            for (int option2 : options.getIds()){
                if (option1 != option2){
                    Pair<Integer, Integer> o1Wins = new Pair<>(option1, option2);
                    Pair<Integer, Integer> o2Wins = new Pair<>(option2, option1);

                    if (strongestPathWeights.get(o1Wins) > strongestPathWeights.get(o2Wins)){
                        pairwiseWinners.put(o1Wins, strongestPathWeights.get(o1Wins));
                    } else {
                        pairwiseWinners.put(o2Wins, strongestPathWeights.get(o2Wins));
                    }
                }
            }
        }

        Map<Integer, Integer> scores = calculateScores(options, pairwiseWinners);

        List<Integer> rankedIds =
                scores.entrySet().stream().sorted(Comparator.comparing(e -> e.getValue() * -1))
                        .map(Map.Entry::getKey).collect(Collectors.toList());

        int[] ranked = new int[rankedIds.size()];

        int i = 0;
        for (int id : rankedIds){
            ranked[i] = id;
            i++;
        }

        return new PrioResult(ranked);
    }

    private Map<Integer, Integer> calculateScores(PrioItems options, Map<Pair<Integer, Integer>, Integer> pairwiseWinners) {
        Map<Integer, Integer> scores = new HashMap<>();

        for (Pair<Integer, Integer> winner : pairwiseWinners.keySet()){

            int previousScore = scores.containsKey(winner.getKey()) ? scores.get(winner.getKey()) : 0;

            scores.put(winner.getKey(), pairwiseWinners.get(winner) + previousScore);
        }

        for(int option : options.getIds()){
            if (!scores.containsKey(option))
                scores.put(option, 0);
        }

        return scores;
    }

    private Map<Pair<Integer, Integer>, Integer> calculateStrongestPathWeights(PrioItems options, SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> pwpGraph) {
        Map<Pair<Integer, Integer>, Integer> strongestPathWeights = new HashMap<>();

        for (int option1 : options.getIds()){
            for (int option2 : options.getIds()){
                if (option1 != option2){
                    Pair<Integer, Integer> pair = new Pair<>(option1, option2);

                    int e1_2 = weight(pwpGraph, option1, option2);
                    int e2_1 = weight(pwpGraph, option2, option1);

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
        return strongestPathWeights;
    }

    private int weight(SimpleDirectedWeightedGraph<Integer, PrioWeightedEdge> pwpGraph, int option1, int option2) {

        PrioWeightedEdge edge = pwpGraph.getEdge(option1, option2);

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

            for (int option1 : ranking){

                for (int i = currentOptionIndex+1; i < ranking.length; i++) {

                    int option2 = ranking[i];

                    if (!prefsGraph.containsEdge(option1, option2)) {
                        prefsGraph.addEdge(option1, option2);
                        continue;
                    }

                    PrioWeightedEdge e = prefsGraph.getEdge(option1, option2);

                    prefsGraph.setEdgeWeight(e, prefsGraph.getEdgeWeight(e) + 1.0);
                }
                currentOptionIndex++;
            }
        }

        return prefsGraph;
    }


}

