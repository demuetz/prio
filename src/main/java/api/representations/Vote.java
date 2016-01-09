package api.representations;

public class Vote {
    private String userName;
    private String sessionKey;
    private int[] orderedOptions;

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

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
