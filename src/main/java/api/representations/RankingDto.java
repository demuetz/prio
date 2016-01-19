package api.representations;

import domain.sessions.RankedItem;
import domain.sessions.Ranking;

@SuppressWarnings("unused")
public class RankingDto {
    private final int voteCount;
    private final RankedItem[] items;

    public RankingDto(Ranking prioResult) {
        this.voteCount = prioResult.getVoteCount();
        this.items = prioResult.getItems();
    }

    public int getVoteCount() {
        return voteCount;
    }

    public RankedItem[] getItems() {
        return items;
    }
}
