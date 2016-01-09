package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PrioItems implements Iterable<PrioItem> {

    private final List<PrioItem> items;

    public PrioItems(List<PrioItem> items) {

        this.items = items;
    }

    public static PrioItems fromTexts(String... texts) {

        List<PrioItem> items = new ArrayList<PrioItem>();

        int id = 1;

        for (String text : texts){
            items.add(PrioItem.withIdAndText(id++, text));
        }

        return new PrioItems(items);
    }

    public Iterator<PrioItem> iterator() {
        return items.iterator();
    }
}
