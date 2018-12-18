package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainActivityController {
    @FXML
    Button addButton;
    @FXML
    Button createButton;
    @FXML
    Button openButton;
    @FXML
    AnchorPane anchorPane;
    @FXML
    TextField path;
    @FXML
    ComboBox<String> comboBox;

    private int x_text_field = 104, x_combo_box = 335,
            y = 53, delta_y = 39,
            height = 31, width = 212;

    public void initialize() {
        comboBox.setValue("Integer");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                y += delta_y;

                TextField newTextField = new TextField();
                newTextField.setLayoutX(x_text_field);
                newTextField.setLayoutY(y);
                newTextField.setPrefHeight(height);
                newTextField.setPrefWidth(width);

                anchorPane.getChildren().add(newTextField);

                ObservableList<String> types = FXCollections.observableArrayList("Integer", "Float", "String", "Phone number");
                ComboBox<String> newComboBox = new ComboBox<String>(types);
                newComboBox.setLayoutX(x_combo_box);
                newComboBox.setLayoutY(y);
                newComboBox.setPrefHeight(height);
                newComboBox.setPrefWidth(width);

                newComboBox.getSelectionModel().selectedItemProperty()
                        .addListener(new ChangeListener<String>() {
                            public void changed(ObservableValue<? extends String> observable,
                                                String oldValue, String newValue) {
                                newComboBox.setValue(newValue);
                            }
                        });

                anchorPane.getChildren().add(newComboBox);
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileWorker fileWorker = FileWorker.getEntity(path.getText());
                String result = "";

                if (anchorPane.getChildren().get(0).getClass().equals(Label.class)) {
                    anchorPane.getChildren().remove(0);
                }

                try {
                    for (int i = 4; i < anchorPane.getChildren().size(); i += 2) {
                        TextField textField = (TextField) anchorPane.getChildren().get(i);
                        ComboBox<String> comboBox = (ComboBox<String>) anchorPane.getChildren().get(i + 1);
                        if (textField.getText().equals("") || comboBox.getValue().toString() == null) {
                            addErrorLabel();
                            return;
                        }
                        result += textField.getText() + "@" + comboBox.getValue().toString() + ";";
                    }
                } catch (Exception e) {
                    addErrorLabel();
                    return;
                }

                fileWorker.rewrite(result + "\n");
                openBdActivity();
            }
        });

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                openBdActivity();
            }
        });
    }

    private void addErrorLabel() {
        Label label = new Label("Please, check\nthat the \nfields are \nfilled!");
        label.setLayoutX(14);
        label.setLayoutY(100);
        label.setPrefHeight(150);
        label.setPrefWidth(150);
        anchorPane.getChildren().add(0, label);
    }

    private void openBdActivity() {
        try {
            if (path.getText().equals("")) {
                addErrorLabel();
                return;
            }
            FileWorker fileWorker = FileWorker.getEntity(path.getText());
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("bd_activity.fxml"));
            Scene scene = new Scene(root);
            stage.setTitle("BD");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            Stage thisStage = (Stage) addButton.getScene().getWindow();
            thisStage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
