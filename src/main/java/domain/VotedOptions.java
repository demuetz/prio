package domain;

public class VotedOptions {
    public VotedOptions(int[] ids) {

    }

    public static VotedOptions withIds(int[] ids) {
        return new VotedOptions(ids);
    }
}
