package domain.votes;

import domain.Participant;

public class Vote {
    private final Participant participant;
    private final VotedOptions options;

    public Vote(Participant participant, VotedOptions options) {

        this.participant = participant;
        this.options = options;
    }

    public static Vote fromParticipantNameWithOptions(String participantName, int... optionIds) {
        return new Vote(Participant.withName(participantName), VotedOptions.withIds(optionIds));
    }

    public Participant getParticipant() {
        return participant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vote vote = (Vote) o;

        return participant.equals(vote.participant) && options.equals(vote.options);

    }

    @Override
    public int hashCode() {
        int result = participant.hashCode();
        result = 31 * result + options.hashCode();
        return result;
    }
}
