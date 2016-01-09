package domain;

public class Participant {
    private final String name;

    public Participant(String name) {

        this.name = name;
    }

    public static Participant withName(String hansi) {
        return new Participant(hansi);
    }
}
