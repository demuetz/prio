package api.representations;

public class PrioSession {
    private String key;


    public static PrioSession withKey(String key){
        PrioSession s = new PrioSession();

        s.setKey(key);

        return s;
    }






    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
