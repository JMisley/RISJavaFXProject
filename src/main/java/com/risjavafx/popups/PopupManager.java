package com.risjavafx.popups;

import com.risjavafx.components.main.Main;
import com.risjavafx.Miscellaneous;
import com.risjavafx.pages.PageManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Popup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PopupManager {
    private static final Map<Popups, Parent> cache = new HashMap<>();

    public static Popup popupMenu = new Popup();
    public static Popup largePopupMenu = new Popup();
    public static Popup popupAlert = new Popup();
    public static Popup customPopup = new Popup();
    private static final ArrayList<Popups> currentPopups = new ArrayList<>();
    static Miscellaneous misc = new Miscellaneous();

    // Method to create a popup menu
    public static void createPopup(Popups popups) {
        if (!popups.isCachable()) {
            loadPopupsToCache(new Popups[]{popups});
        }
        Parent popupRoot = cache.get(popups);

        popups.getPopup().getContent().add(popupRoot);
        PageManager.getRoot().setDisable(true);
        popups.getPopup().show(Main.usableStage);
        currentPopups.add(popups);

        Main.usableStage.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (aBoolean) popups.getPopup().setOpacity(0f);
            else popups.getPopup().setOpacity(1f);
        });
    }

    public static void loadPopupsToCache(Popups[] popups) {
        try {
            for (Popups popup : popups) {
                Parent root = FXMLLoader.load(Objects.requireNonNull(PopupManager.class.getResource(popup.getFilename())));
                root.getStylesheets().add(Objects.requireNonNull(PageManager.class.getResource("stylesheet/styles.css")).toExternalForm());

                switch (popup.getType()) {
                    case "MENU" -> {
                        popup.getPopup().setY(misc.getScreenHeight() / 2 - Popups.getMenuDimensions()[0] / 2);
                        popup.getPopup().setX(misc.getScreenWidth() / 2 - Popups.getMenuDimensions()[1] / 2);
                    }
                    case "LARGE_MENU" -> {
                        popup.getPopup().setY(misc.getScreenHeight() / 2 - Popups.getLargeMenuDimensions()[0] / 2);
                        popup.getPopup().setX(misc.getScreenWidth() / 2 - Popups.getLargeMenuDimensions()[1] / 2);
                    }
                    case "ALERT" -> {
                        popup.getPopup().setY(misc.getScreenHeight() / 2 - Popups.getAlertDimensions()[0] / 2);
                        popup.getPopup().setX(misc.getScreenWidth() / 2 - Popups.getAlertDimensions()[1] / 2);
                    }
                    case "CUSTOM" -> {
                        popup.getPopup().setY(misc.getScreenHeight() / 2 - Popups.getCustomDimensions()[0] / 2);
                        popup.getPopup().setX(misc.getScreenWidth() / 2 - Popups.getCustomDimensions()[1] / 2);
                    }
                }
                cache.put(popup, root);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removePopup() {
        if (currentPopups.get(currentPopups.size() - 1).getType().equals("ALERT")) {
            if (currentPopups.size() <= 1)
                PageManager.getRoot().setDisable(false);
            currentPopups.get(currentPopups.size() - 1).getPopup().getContent().clear();
            currentPopups.get(currentPopups.size() - 1).getPopup().hide();
            currentPopups.remove(currentPopups.size() - 1);
        } else {
            try {
                for (Popups popups : currentPopups) {
                    PageManager.getRoot().setDisable(false);
                    popups.getPopup().getContent().clear();
                    popups.getPopup().hide();
                    currentPopups.clear();
                }
            } catch (Exception ignore) {
            }
        }
    }

    public static void removePopup(Popups popup) {
        if (currentPopups.contains(popup)) {
            PageManager.getRoot().setDisable(false);
            popup.getPopup().getContent().clear();
            popup.getPopup().hide();
            currentPopups.remove(popup);
            System.out.println("removed");
        }
    }
}