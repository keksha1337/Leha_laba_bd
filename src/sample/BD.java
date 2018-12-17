package sample;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class BD {
    private HashMap<Integer, Entity> entities;
    private FileWorker fileWorker;

    public BD() {
        entities = new HashMap<>();
        fileWorker = new FileWorker();
    }

    public void create(String path) throws IOException {
        fileWorker.create(path);
    }

    public void addEntity(Entity entity) {
        entities.put(Collections.max(new ArrayList<Integer>(entities.keySet())) + 1, entity);
    }

    public void removeEntity(String paramsName, String value) {

    }

    public String getEntityParams(int id, String params) {
        return entities.get(id).getParams(params);
    }



}
