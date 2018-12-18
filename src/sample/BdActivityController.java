package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
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
    VBox vBox;

    int x = 20, y = 40, d_y = 43, width = 450, height = 31;

    private FileWorker fileWorker;

    public void initialize() {
        fileWorker = FileWorker.getEntity();
        BD bd = new BD();
        ArrayList<String> strFiles = fileWorker.getAllStrings(fileWorker.getPath());
        String[] firstLine = strFiles.get(0).split(";");
        strFiles.remove(0);


        String label = "";
        for (String str : firstLine) {
            label += str.split("@")[0] + ";";
        }
        vBox.getChildren().add(new Label(label));
        for (String str : strFiles) {
            Entity entity = new Entity();
            String[] params = str.split(";");
            for (int i = 0; i < firstLine.length; i++) {
                entity.addParams(firstLine[i].split("@")[0], params[i]);
            }
            addTextField(str);
            bd.addEntity(entity);
        }

        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                addTextField("");
            }
        });

        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBox.getChildren().remove(1,vBox.getChildren().size());
                String result = "";
                for (String str : firstLine) {
                    result += str + ";";
                }
                fileWorker.rewrite(result);
            }
        });

        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                vBox.getChildren().remove(vBox.getChildren().size() - 1);
                String result = "";
                ArrayList<String> strings = fileWorker.getAllStrings(fileWorker.getPath());
                strings.remove(strings.size() - 1);
                for (String str : strings) {
                    result += str + "\n";
                }
                fileWorker.rewrite(result);
            }
        });

        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String result = "";
                for (String str : firstLine) {
                    result += str + ";";
                }
                result += "\n";
                for (int i = 1; i < vBox.getChildren().size(); i++) {
                    TextField tf = (TextField)vBox.getChildren().get(i);
                    result += tf.getText() + "\n";
                }
                fileWorker.rewrite(result);
            }
        });

        menuButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    FileWorker fileWorker = FileWorker.getEntity();
                    Stage stage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("main_activity.fxml"));
                    Scene scene = new Scene(root);
                    stage.setTitle("BD");
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
                    Stage thisStage = (Stage) addButton.getScene().getWindow();
                    thisStage.close();
                } catch (IOException e) {

                }
            }
        });

    }

    private void addTextField(String str) {
        TextField textField = new TextField(str);
        textField.setLayoutX(x);
        textField.setLayoutY(y);
        textField.setPrefWidth(width);
        textField.setPrefHeight(height);
        vBox.getChildren().add(textField);
        y += d_y;
    }

}
