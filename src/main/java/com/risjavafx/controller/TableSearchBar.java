package com.risjavafx.controller;

import com.risjavafx.model.AdminData;
import com.risjavafx.model.Miscellaneous;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableSearchBar implements Initializable {
    public Button addButton;
    public Label searchLabel;
    public TextField textField;
    static Miscellaneous misc = new Miscellaneous();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        resizeElements();
        AdminData.textField = textField;
    }

    public <E> void createSearchBar(HBox tableSearchBar, Class<E> thisClass) {
        try {
            URL navigationBarComponent = thisClass.getResource("components/TableSearchBar.fxml");
            tableSearchBar.getChildren().setAll((Node) FXMLLoader.load(Objects.requireNonNull(navigationBarComponent)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resizeElements() {
        addButton.setPrefHeight(misc.getScreenHeight() * .05);
        addButton.setPrefWidth(misc.getScreenWidth() * .08);

        textField.setPrefHeight(misc.getScreenHeight() * .05);
        textField.setPrefWidth(misc.getScreenWidth() * .3);

        double fontSize;
        if ((misc.getScreenWidth() / 80) < 20) {
            fontSize = misc.getScreenWidth() / 80;
        } else {
            fontSize = 20;
        }
        searchLabel.setStyle("-fx-font-size: " + fontSize);
        addButton.setStyle("-fx-font-size: " + fontSize);
        textField.setStyle("-fx-font-size: " + (fontSize - 2) + "; -fx-font-family: 'Arial'");
    }
}
