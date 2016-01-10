package domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class PrioItems implements Iterable<PrioItem>, Collection<PrioItem> {

    private final List<PrioItem> items;

    public PrioItems(List<PrioItem> items) {

        this.items = items;
    }

    public static PrioItems empty() {
        return new PrioItems(new ArrayList<>());
    }

    public PrioItems join(PrioItems other) {
        //ToDo: dedup

        ArrayList<PrioItem> jointList = new ArrayList<>(items);
        jointList.addAll(other.items);

        return new PrioItems(jointList);
    }

    public static PrioItems fromTexts(String... texts) {

        List<PrioItem> items = new ArrayList<>();

        int id = 1;

        for (String text : texts){
            items.add(PrioItem.withIdAndText(id++, text));
        }

        return new PrioItems(items);
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return items.contains(o);
    }

    public Iterator<PrioItem> iterator() {
        return items.iterator();
    }

    @Override
    public Object[] toArray() {
        return items.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return items.toArray(a);
    }

    @Override
    public boolean add(PrioItem prioItem) {
        return items.add(prioItem);
    }

    @Override
    public boolean remove(Object o) {
        return items.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return items.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends PrioItem> c) {
        return items.addAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return items.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return items.retainAll(c);
    }

    @Override
    public void clear() {
        items.clear();
    }


}
