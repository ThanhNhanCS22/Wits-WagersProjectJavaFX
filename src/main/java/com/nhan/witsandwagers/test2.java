package com.nhan.witsandwagers;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
public class test2 extends Application {
    public void start(Stage primaryStage) {
        StackPane stackPane = new StackPane();

        // Tạo GridPane
        GridPane gridPane = new GridPane();
        gridPane.setGridLinesVisible(true); // Hiển thị đường kẻ lưới (tuỳ chọn)
        gridPane.setStyle("-fx-background-color: lightblue;");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Label cell = new Label("Row " + i + ", Col " + j);
                cell.setStyle("-fx-border-color: black; -fx-padding: 10;");
                GridPane.setConstraints(cell, j, i);
                gridPane.getChildren().add(cell);
            }
        }

        // Một thành phần khác chồng lên GridPane
        Label overlayText = new Label("Overlay Text");
        overlayText.setStyle("-fx-font-size: 30px; -fx-text-fill: red;");

        // Thêm vào StackPane
        stackPane.getChildren().addAll(gridPane, overlayText);

        // Căn chỉnh overlayText ở giữa
        StackPane.setAlignment(overlayText, Pos.CENTER);

        // Scene và Stage
        Scene scene = new Scene(stackPane, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}
