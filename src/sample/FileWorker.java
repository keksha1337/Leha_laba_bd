package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class FileWorker {
    private String path;

    public FileWorker(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void create() throws IOException {
        rewrite("");
    }

    public void write(String str) throws IOException {
        Files.write(Paths.get(path), str.getBytes(), StandardOpenOption.APPEND);
    }

    public void rewrite(String str) {
        try {
            if (Files.exists(Paths.get(path))) {
                Files.delete(Paths.get(path));
            }
            Files.write(Paths.get(path), str.getBytes(), StandardOpenOption.CREATE_NEW);
        } catch (IOException e) {

        }
    }

    public ArrayList<String> getAllStrings() throws IOException {
        ArrayList<String> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while (reader.ready()) {
            result.add(reader.readLine());
        }
        reader.close();
        return result;
    }
}
