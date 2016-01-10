package domain.sessions;

public class PrioItem {
    private int id;
    private String text;

    public PrioItem(int id, String text) {

        this.id = id;
        this.text = text;
    }


    public static PrioItem withIdAndText(int id, String text) {

        return new PrioItem(id, text);
    }


    public int getId() {
        return id;
    }


    public String getText() {
        return text;
    }

}
