package sample;

import java.io.File;
import java.io.IOException;

public class FileWorker {
    public void create(String path) throws IOException {
        File file = new File(path);
        if (!file.createNewFile()) {
            file.delete();
            file.createNewFile();
        }
    }

}
