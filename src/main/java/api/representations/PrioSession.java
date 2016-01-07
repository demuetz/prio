package api.representations;

import java.util.List;

public class PrioSession {
    private String key;
    private List<Option> options;


    public static PrioSession withKeyAndOptions(String key, List<Option> options) {
        PrioSession s = new PrioSession();

        s.setKey(key);
        s.setOptions(options);

        return s;
    }






    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }
}
