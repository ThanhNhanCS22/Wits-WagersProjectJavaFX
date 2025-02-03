package com.nhan.witsandwagers;

import com.nhan.witsandwagers.Gui.GUI;
import javafx.application.Application;

/**
 * The Launcher class is the entry point for the Wits and Wagers application.
 */
public class Launcher {
    /**
     * The main method that launches the JavaFX application.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Application.launch(GUI.class, args);
    }
}
