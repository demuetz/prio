package domain.resolvers;

public class Pair {
    public int winner;
    public int loser;

    public static Pair pair(int winner, int loser){

        Pair p = new Pair();
        p.winner = winner;
        p.loser = loser;
        return p;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pair pair = (Pair) o;

        if (winner != pair.winner) return false;
        return loser == pair.loser;

    }

    @Override
    public int hashCode() {
        int result = winner;
        result = 31 * result + loser;
        return result;
    }
}
