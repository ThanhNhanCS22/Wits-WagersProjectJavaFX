package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        String text = "Đây là một đoạn văn bản rất dài, có thể có nhiều câu và thông tin. "
                + "Nội dung này cần được chia thành hai cột để dễ đọc hơn.".repeat(50);

        // Chia văn bản thành 2 phần (2 cột)
        String part1 = text.substring(0, text.length() / 2);  // Phần 1
        String part2 = text.substring(text.length() / 2);     // Phần 2

        // Tạo TextFlow để hiển thị các phần văn bản
        TextFlow textFlow = new TextFlow();

        Text text1 = new Text(part1);  // Phần 1 của văn bản
        Text text2 = new Text(part2);  // Phần 2 của văn bản

        // Đưa các phần vào TextFlow
        textFlow.getChildren().addAll(text1, text2);

        // Sử dụng HBox để căn chỉnh các TextFlow thành các cột
        HBox hbox = new HBox(10); // Khoảng cách giữa các cột
        hbox.getChildren().add(textFlow);

        // Tạo Scene và hiển thị
        Scene scene = new Scene(hbox, 600, 100);
        primaryStage.setTitle("Chia Cột Văn Bản");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
