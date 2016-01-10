package domain;

public class Participant {
    private final String name;

    public Participant(String name) {

        this.name = name;
    }

    public static Participant withName(String name) {
        return new Participant(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Participant that = (Participant) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }
}
