package domain.votes;

import domain.Participant;

public class Vote {
    private final Participant participant;
    private final VotedOptions options;

    public Vote(Participant participant, VotedOptions options) {

        this.participant = participant;
        this.options = options;
    }

    public boolean wasCastBy(Participant participant) {
        return this.participant.equals(participant);
    }
}
