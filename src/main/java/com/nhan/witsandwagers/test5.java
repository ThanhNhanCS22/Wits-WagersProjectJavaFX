package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test5 extends Application {
    @Override
    public void start(Stage stage) {
        // Tạo Text cho chuỗi
        Text verticalText = new Text("Hello");
        verticalText.setRotate(90); // Xoay 90 độ

        // Tạo Button và đặt Text vào
        Button button = new Button();
        button.setGraphic(verticalText); // Đặt Text vào Button

        // Căn chỉnh kích thước
        button.setMinSize(100, 100); // Đặt kích thước tối thiểu để đảm bảo vừa với Text

        // Đặt Button vào StackPane
        StackPane root = new StackPane(button);
        Scene scene = new Scene(root, 200, 200);

        // Thiết lập và hiển thị Stage
        stage.setTitle("Vertical Text Button");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
