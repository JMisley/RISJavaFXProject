package com.risjavafx.components;

import com.risjavafx.Miscellaneous;
import com.risjavafx.pages.PageManager;
import com.risjavafx.pages.Pages;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static Stage usableStage;
    Miscellaneous misc = new Miscellaneous();

    @Override
    public void start(Stage primaryStage) {
        usableStage = primaryStage;

        Scene scene = new Scene(new BorderPane());
        PageManager.loadPageToCache(Pages.PROGRESS);
        PageManager.setScene(scene);
        PageManager.switchPage(Pages.LOGIN);

        usableStage.setScene(scene);
        usableStage.setMinWidth(misc.getScreenWidth());
        usableStage.setMinHeight(misc.getScreenHeight());
        usableStage.setMaximized(false);
        usableStage.initStyle(StageStyle.UNDECORATED);
        usableStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}