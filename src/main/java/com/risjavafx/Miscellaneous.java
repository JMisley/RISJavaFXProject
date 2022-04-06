package com.risjavafx;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

import java.util.Arrays;

public class Miscellaneous {
    Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    public double getScreenWidth() {
        return primaryScreenBounds.getWidth();
    }

    public double getScreenHeight() {
       return primaryScreenBounds.getHeight();
    }
}
