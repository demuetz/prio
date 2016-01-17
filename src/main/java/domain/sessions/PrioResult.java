package domain.sessions;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public class PrioResult {
    private int[] ranking;

    @JsonCreator
    public PrioResult(int[] ranking) {
        this.ranking = ranking;
    }

    public int[] getRanking() {
        return ranking;
    }

    public static PrioResult error(String s) {
        return new PrioResult(new int[]{-1});
    }

    @Override
    public String toString() {
        return "PrioResult{" +
                "ranking=" + Arrays.toString(ranking) +
                '}';
    }
}
