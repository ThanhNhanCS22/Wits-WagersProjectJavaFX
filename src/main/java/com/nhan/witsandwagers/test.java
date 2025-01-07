package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;

import javafx.scene.text.Text;
import javafx.stage.Stage;

public class test extends Application {

    @Override
    public void start(Stage stage) {
        // Create a GridPane to hold the buttons
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(20, 20, 20, 20));

        // Array of button labels
        String[] buttonLabels = {
                "Smaller\nthan the\nsmallest guess",
                "6 to 1",
                "5 to 1",
                "4 to 1",
                "3 to 1",
                "2 to 1",
                "3 to 1",
                "4 to 1",
                "5 to 1"
        };
        Text text = new Text(""  ) ;
        text.setRotate(-90);
        VBox vBox1 =  new VBox() ;
        vBox1.getChildren().add(text);
        Button button = new Button() ;
        button.setGraphic(vBox1) ;
        gridPane.add(button,0,0);
        button.setPrefWidth(120); // Set button width
        button.setPrefHeight(800); // Set button height to make it taller
        button.setStyle(
                "-fx-background-color: transparent;" + // Transparent body
                        "-fx-border-color: white;" +           // White border
                        "-fx-border-width: 3;" +              // Border thickness
                        "-fx-text-fill: white;" +             // White text
                        "-fx-font-size: 14px;" +              // Font size for better readability
                        "-fx-border-radius: 20;" +            // Rounded border corners
                        "-fx-padding: 10;"                    // Padding inside button
        );


        // Create buttons dynamically
        for (int i = 1; i < 8; i++) {

            VBox vBox = new VBox() ;
            vBox.setSpacing(1000) ;
            Text text1 = new Text(buttonLabels[i]) ;
            Text text2 = new Text(buttonLabels[i]) ;
            text2.setRotate(180) ;
            vBox.getChildren().addAll(text1, text2) ;


            Button button2 = new Button();
            button2.setGraphic(vBox) ;

            button2.setPrefWidth(120); // Set button width
            button2.setPrefHeight(800); // Set button height to make it taller
            button2.setStyle(
                    "-fx-background-color: transparent;" + // Transparent body
                            "-fx-border-color: white;" +           // White border
                            "-fx-border-width: 3;" +              // Border thickness
                            "-fx-text-fill: white;" +             // White text
                            "-fx-font-size: 14px;" +              // Font size for better readability
                            "-fx-border-radius: 20;" +            // Rounded border corners
                            "-fx-padding: 10;"                    // Padding inside button
            );

            // Add the button to the grid pane
            gridPane.add(button2,i,0);
        }

        // Create a Scene and add the grid pane
        Scene scene = new Scene(gridPane, 1200, 400); // Adjust the scene size
        gridPane.setStyle("-fx-background-color: #2E8B57;"); // Dark green background for contrast

        // Set the stage
        stage.setTitle("Wits & Wagers");
        stage.setScene(scene);
        stage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}