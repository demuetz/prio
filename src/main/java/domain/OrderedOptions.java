package domain;

public class OrderedOptions {
    public OrderedOptions(int[] ids) {

    }

    public static OrderedOptions withIds(int[] ids) {
        return new OrderedOptions(ids);
    }
}
