package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainActivityController {
    @FXML
    Button addButton;
    @FXML
    Button createButton;
    @FXML
    Button openButton;
    @FXML
    AnchorPane anchorPane;

    private int x_text_field = 104, x_combo_box = 335,
            y = 53, delta_y = 39,
            height = 31, width = 212;

    public void initialize() {
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

                anchorPane.getChildren().add(newComboBox);
            }
        });

        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        openButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });
    }
}
