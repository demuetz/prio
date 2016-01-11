package domain.resolvers;

import static domain.resolvers.Pair.pair;

public class PairwisePref {
    public Pair pair;
    public int count;

    public static PairwisePref pwp(int winner, int loser, int count){

        PairwisePref p = new PairwisePref();
        p.pair = pair(winner, loser);
        p.count = count;
        return p;
    }


    @Override
    public String toString() {
        return "pwp{" + pair.winner + "," + pair.loser + "," + count + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PairwisePref that = (PairwisePref) o;

        return count == that.count && pair.equals(that.pair);
    }

    @Override
    public int hashCode() {
        int result = pair.hashCode();
        result = 31 * result + count;
        return result;
    }
}
