package com.nhan.witsandwagers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class GameController {
    @FXML
    public void startGame() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Started");
        alert.setHeaderText(null);
        alert.setContentText("The game has started!");
        alert.showAndWait();
    }

    @FXML
    public void showScores() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Current Scores");
        alert.setHeaderText(null);
        alert.setContentText("Displaying player scores here...");
        alert.showAndWait();
    }

    @FXML
    public void exitGame() {
        System.exit(0);
    }
}
