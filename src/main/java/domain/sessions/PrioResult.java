package domain.sessions;

public class PrioResult {
    private int[] ranking;

    public PrioResult(int[] ranking) {
        this.ranking = ranking;
    }

    public int[] getRanking() {
        return ranking;
    }

    public static PrioResult error(String s) {
        return new PrioResult(new int[]{-1});
    }
}
