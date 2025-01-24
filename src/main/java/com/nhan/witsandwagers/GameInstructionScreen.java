package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameInstructionScreen extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Tạo Label chứa nội dung hướng dẫn
        Label instructionLabel = new Label(
                "Welcome to the Game!\n\n" +
                        "Instructions:\n" +
                        "1. Use the arrow keys to move your character.\n" +
                        "2. Avoid obstacles and enemies.\n" +
                        "3. Collect coins to increase your score.\n" +
                        "4. Reach the finish line to win the game.\n\n" +
                        "Tips:\n" +
                        "- Plan your moves carefully.\n" +
                        "- Use power-ups to overcome difficult challenges.\n" +
                        "- Have fun!\n\n" +
                        "Good luck!"
        );
        instructionLabel.setWrapText(true); // Cho phép xuống dòng tự động

        // Đặt Label trong VBox để dễ dàng tùy chỉnh
        VBox content = new VBox(10, instructionLabel);
        content.setStyle("-fx-padding: 20; -fx-background-color: #f0f0f0;");

        // Tạo ScrollPane để hỗ trợ cuộn
        ScrollPane scrollPane = new ScrollPane(content);
        scrollPane.setFitToWidth(true); // Phù hợp với chiều rộng
        scrollPane.setStyle("-fx-background: #f0f0f0;");


        Scene scene = new Scene(scrollPane, 600, 400); // Kích thước màn hình

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game Instructions");
        primaryStage.show();
    }
}