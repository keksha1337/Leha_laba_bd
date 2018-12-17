package sample;

import java.util.HashMap;

public class Entity {
    private HashMap<String, String> params;

    public String getParams(String key) {
        return params.get(key);
    }

    public void addParams(String key, String value) {
        params.put(key, value);
    }
}
