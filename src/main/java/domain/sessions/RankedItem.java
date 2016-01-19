package domain.sessions;

@SuppressWarnings("unused")
public class RankedItem {
    private final int itemId;
    private final String itemText;
    private final int rank;
    private final int score;

    public RankedItem(int itemId, String itemText, int rank, int score) {

        this.itemId = itemId;
        this.itemText = itemText;
        this.rank = rank;
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RankedItem that = (RankedItem) o;

        return itemId == that.itemId
                && rank == that.rank
                && score == that.score
                && (itemText != null ? itemText.equals(that.itemText) : that.itemText == null);

    }

    @Override
    public int hashCode() {
        int result = itemId;
        result = 31 * result + (itemText != null ? itemText.hashCode() : 0);
        result = 31 * result + rank;
        result = 31 * result + score;
        return result;
    }

    @Override
    public String toString() {
        return "RankedItem{" +
                "itemId=" + itemId +
                ", rank=" + rank +
                '}';
    }

    public int getId() {
        return itemId;
    }

    public String getText() {
        return itemText;
    }

    public int getRank() {
        return rank;
    }

    public int getScore() {
        return score;
    }
}
