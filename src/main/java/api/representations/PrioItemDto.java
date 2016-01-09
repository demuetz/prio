package api.representations;

import domain.PrioItem;

import java.util.ArrayList;
import java.util.List;

public class PrioItemDto {
    private int id;
    private String text;

    public static PrioItemDto withIdAndText(int id, String text) {

        PrioItemDto o = new PrioItemDto();

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

    public static List<PrioItemDto> fromItems(Iterable<PrioItem> items) {

        ArrayList<PrioItemDto> res = new ArrayList<>();

        for (PrioItem i : items){
            res.add(withIdAndText(i.getId(), i.getText()));
        }

        return res;
    }
}
