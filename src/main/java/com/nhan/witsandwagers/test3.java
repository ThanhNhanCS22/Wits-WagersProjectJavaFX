package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class test3 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Method 1: Using \n for multi-line text
        Button button1 = new Button("Text at the top\nText at the bottom");
        button1.setStyle("-fx-font-size: 16px; -fx-alignment: center;");
        button1.setPrefSize(200, 100);

        // Method 2: Using VBox to layout text
        Button button2 = new Button();
        button2.setPrefSize(200, 100);
        VBox vbox = new VBox();
        vbox.setSpacing(100);
        vbox.setAlignment(javafx.geometry.Pos.CENTER);
        Label text1 =  new Label("Text at the top") ;
        Label text2 = new Label("Text at the bottom") ;
        text2.setRotate(-90) ;
        vbox.getChildren().addAll(
                text1, text2
        );
        button2.setGraphic(vbox);

        // Method 3: Using TextFlow for advanced formatting
        Button button3 = new Button();
        button3.setPrefSize(200, 100);
        TextFlow textFlow = new TextFlow();
        Text topText = new Text("Text at the top\n");
        topText.setStyle("-fx-font-size: 16px; -fx-fill: blue;");
        Text bottomText = new Text("Text at the bottom");
        bottomText.setStyle("-fx-font-size: 16px; -fx-fill: red;");
        textFlow.getChildren().addAll(topText, bottomText);
        button3.setGraphic(textFlow);

        // StackPane to display buttons
        StackPane root = new StackPane();
        VBox buttonBox = new VBox(20); // Add spacing between buttons
        buttonBox.setAlignment(javafx.geometry.Pos.CENTER);
        buttonBox.getChildren().addAll(button1, button2, button3);

        root.getChildren().add(buttonBox);

        Scene scene = new Scene(root, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Multi-Line Button Demo");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
