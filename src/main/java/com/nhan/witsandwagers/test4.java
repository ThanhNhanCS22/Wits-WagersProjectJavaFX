package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class test4 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Tạo nút
        Button customButton = new Button();
        customButton.setText("SMALLER\nTHAN THE SMALLEST GUESS\n6 to 1"); // Dòng chữ
        customButton.setFont(Font.font("Arial", 20)); // Font chữ
        customButton.setAlignment(Pos.CENTER);
        customButton.setWrapText(true); // Để text tự động xuống dòng

        // Cài đặt kích thước
        customButton.setPrefWidth(150);
        customButton.setPrefHeight(400);

        // Cài đặt màu nền và viền
        customButton.setStyle(
                "-fx-background-color: green;" + // Màu nền
                        "-fx-border-color: white;" +    // Màu viền
                        "-fx-border-width: 5px;" +      // Độ dày viền
                        "-fx-text-fill: white;"         // Màu chữ
        );

        // Hiệu ứng DropShadow để làm nút nổi bật
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.BLACK);
        dropShadow.setRadius(10);
        dropShadow.setOffsetX(3);
        dropShadow.setOffsetY(3);
        customButton.setEffect(dropShadow);

        // StackPane chứa nút
        StackPane root = new StackPane(customButton);
        root.setStyle("-fx-background-color: darkgreen;"); // Màu nền tổng thể
        root.setAlignment(Pos.CENTER);

        // Scene
        Scene scene = new Scene(root, 300, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Custom Button Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
