package com.risjavafx.popups;

import com.risjavafx.components.Main;
import com.risjavafx.Miscellaneous;
import com.risjavafx.pages.PageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;

import java.io.IOException;
import java.util.Objects;

public class PopupManager {
    public static Popup popupMenu = new Popup();
    public static Popup popupAlert = new Popup();
    static Miscellaneous misc = new Miscellaneous();

    // Method to create a popup menu. Input a decimals to represent a percentage of the screen height and width
    public static void createPopup(Popups popups) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(PopupManager.class.getResource(popups.getFilename())));

        //PROBLEM
        root.getStylesheets().getClass().getResource(PageManager.root.getStyle());

        if (popups.getType().equals("MENU")) {
            popups.getPopup().setY(misc.getScreenHeight()/2 - Popups.getMenuDimensions()[0]/2);
            popups.getPopup().setX(misc.getScreenWidth()/2 - Popups.getMenuDimensions()[1]/2);
        } else if (popups.getType().equals("ALERT")) {
            popups.getPopup().setY(misc.getScreenHeight()/2 - Popups.getAlertDimensions()[0]/2);
            popups.getPopup().setX(misc.getScreenWidth()/2 - Popups.getAlertDimensions()[1]/2);
        }

        popups.getPopup().getContent().add(root);
        popups.getPopup().show(Main.usableStage);

        Main.usableStage.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (aBoolean) popups.getPopup().setOpacity(0f);
            else popups.getPopup().setOpacity(1f);
        });
    }
}
