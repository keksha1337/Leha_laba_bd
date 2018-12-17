package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.util.ArrayList;

public class BdActivityController {
    @FXML
    AnchorPane anchorPane;
    @FXML
    Button addButton;
    @FXML
    Button clearButton;
    @FXML
    Button deleteButton;
    @FXML
    Button saveButton;
    @FXML
    Button menuButton;
    @FXML
    ScrollPane scrollPane;
    @FXML
    TableView<TableColumn<String, String>> table;

    private FileWorker fileWorker;

    public void initialize() {
        fileWorker = FileWorker.getEntity();
        BD bd = new BD();
        ArrayList<String> strFiles = fileWorker.getAllStrings();
    }



}
