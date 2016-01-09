package api.representations;

public class VoteDto {
    private String userName;
    private int[] orderedOptions;

    public int[] getOrderedOptions() {
        return orderedOptions;
    }

    public void setOrderedOptions(int[] orderedOptions) {
        this.orderedOptions = orderedOptions;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
