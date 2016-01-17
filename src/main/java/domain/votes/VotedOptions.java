package domain.votes;

import java.util.Arrays;

public class VotedOptions {
    private final int[] ids;


    public VotedOptions(int[] ids) {

        this.ids = ids;
    }

    public static VotedOptions withIds(int... ids) {
        return new VotedOptions(ids);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VotedOptions that = (VotedOptions) o;

        return Arrays.equals(ids, that.ids);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(ids);
    }

    public int[] getIds() {
        return ids;
    }
}
