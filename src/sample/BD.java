package sample;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BD {
    private HashMap<Integer, Entity> entities;

    public BD() {
        entities = new HashMap<>();
    }

    public void addEntity(Entity entity) {
        int id;
        try {
            id = Collections.max(new ArrayList<Integer>(entities.keySet())) + 1;
        } catch (Exception e) {
            id = 1;
        }
        entities.put(id, entity);
    }

    public void removeEntity(String paramsName, String value) {
        HashMap<Integer, Entity> result = new HashMap<>();
        for (Map.Entry<Integer, Entity> oneEntity : entities.entrySet()) {
            if(!oneEntity.getValue().contains(paramsName, value)) {
                result.put(oneEntity.getKey(), oneEntity.getValue());
            }
        }
        entities = new HashMap<>(result);
    }

    public String getEntityParams(int id, String params) {
        return entities.get(id).getParams(params);
    }



}
