package api.representations;

public class Option {
    private int id;
    private String text;

    public static Option withIdAndText(int id, String text) {

        Option o = new Option();

        o.setId(id);
        o.setText(text);
        return o;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
