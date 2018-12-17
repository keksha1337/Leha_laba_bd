package sample;

import java.util.HashMap;

public class Entity {
    private HashMap<String, String> params;

    public boolean contains(String key, String value) {
        if (params.get(key) != null) {
            return true;
        }
        return false;
    }

    public String getParams(String key) {
        return params.get(key);
    }

    public void addParams(String key, String value) {
        params.put(key, value);
    }
}
