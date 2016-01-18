package api.representations;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PrioResultDto {
    private final int[] ranking;

    @JsonCreator
    public PrioResultDto(int[] ranking) {
        this.ranking = ranking;
    }

    public int[] getRanking() {
        return ranking;
    }
}
