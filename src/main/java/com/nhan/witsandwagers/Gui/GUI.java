package com.nhan.witsandwagers.Gui;

import com.nhan.witsandwagers.Elements.Player;
import com.nhan.witsandwagers.Logic.Game;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;




public class GUI extends Application {


    private boolean betScreenisShowed = false;  //To check the betting Screen is showed or not.
                                                // If not clear the mainMenuPane and add new UIs elements
                                                // to the slot (table) scene.

    private final Button gameRuleButton = new Button("How To Play"); //Click on this button to see game's rules

    private final StackPane mainMenuPane = new StackPane(); //Main pane pf the game to stack UI elements

    private final Scene mainMenuScene = new Scene(mainMenuPane); //to show the game

    private final Game game = new Game(); //To access  game's logic

    private final Button nextButton = new Button("Next"); // Implement once in global scope and reuse in
    // many scenes for moving to next other scene.

    private final Button backButton = new Button("Back"); // Similar to nextButton,
    // Implement once in global scope and reuse in
    // many scenes for moving back.

    private final VBox vButtonBox = new VBox(); //To UIs elements such as Button, Label, etc., vertically

    private final HBox hButtonBox = new HBox() ; // To arrange buttons horizontally

    private final Label questionNumTitleOfShowGuessScreen = new Label( ) ;

    private final Label questionTitle = new Label() ; // To display the question  in guess scene.

    private final VBox vTitleBox =new VBox() ;

    private final Label requestPlayerLabel = new Label() ;//request players to input their guess

    private final Label inputNameLabel = new Label();//Label to request players input theie names

    private final TextField nameField = new TextField(); //Field to for inputing name

    private final TextField answerField = new TextField() ;//Filed for inputing guesses

    private int numPlayers;// Represents for total of players who are playing the game.

    private final Label notificationErrorLabel = new Label();//This label is used to display notification when edge cases occur.

    private final Button pauseButton = new Button();// used to pause game

    private final Button playGameButton = new Button();// Use to start game or continue in pause scene

    private final Button exitGameButton = new Button("Exit Game");// Used to exit game
    private final Label readyLabel = new Label("Are you ready?");// Only used to display the word :"Arer you ready?"

//    private final Label mainTitle = new Label("WITS & WAGERS");

    private final Label winnersTitle = new Label("WINNER(S)") ;// Only used to display the word that it is initialized

    private int questionCount ; //Start with 0 and when its reach 7, the game end.

    private int playerCount ;// Similar to questionCount, start with 0 and qhen it reach numPlayers, move to other stages

    private final Label guessCountdownLabel = new Label();//the number countdown label in guess scene and the scene before the game start.

    private final Label betCountDownLabel = new Label(); //The number countdown label for the slots and betting scenes
    private final Timeline countdown = new Timeline(); // To set a timer.
    private final Label note = new Label() ;//The note in guess scene
    private int currentSecond ; // Used to set the time for gameplay scenes.
    private Boolean paused = false; //Mainly used for logical displaying between slots and betting scenes
    private final Label questionAnnoucementScreenLabel = new Label() ; //Only used in question annoucement screen
    private final Label playerNameLabel= new Label() ; //Used to display players' name
    private final Label playerBalanceLabel = new Label() ; // Used to display players' money
    private final Label bonusLabel = new Label("Bonus Summary") ; // Only used to display  the word "Bonus Summary"
    private final PauseTransition pause = new PauseTransition(); //To set time delay before moving to next scene
    private final Label correctAnswerLabel = new Label() ; //used to display correct Answer
    private final Label winningGuessLabel = new Label() ;// used to display winning guess
    private final Label stepLabel = new Label(); //Used to display steps in rule scence
    private final Label chosenSLotLabel = new Label() ;// used to Display the slot that players chose
    private int winnersCount  = 0 ; //used to count the number of winners
    private boolean gameStarted = false ; //If game start -> does not need to running agiain the method initialize
    // to format the UI elements using embedded CSS
    private final Label contentLabel = new Label(); //Display some contents in rule scee
    private final Button homeButton = new Button("Home"); // back to the starting scene

    private boolean gotBack = false; // if gotback == true, does not need to load the other question set

    private int countStep = 0; // to count steps in rule scene




    private final String[] buttonMoneyLabels = new String[]{
            "$100",
            "$500",
            "$1000",
            "$5000",
            "$10000"
    }; // Just simply money labels.



    private final HBox bettingMat= new HBox() ;  // The slots or betting mat.

    //This method used to set background for the whole game
    private void setBackgroundImage(Pane pane) {


        ImageView backgroundImage = new ImageView(new Image(getClass().getResource("/images/Artboard 1.png").toExternalForm()));
        backgroundImage.setFitWidth(1920); // Set the width to match the pane
        backgroundImage.setFitHeight(1080); // Set the height to match the pane
        backgroundImage.setPreserveRatio(false); // Preserve the aspect ratio

        // Bind the image size to the window size
        backgroundImage.fitWidthProperty().bind(pane.widthProperty());
        backgroundImage.fitHeightProperty().bind(pane.heightProperty());

        // Add the background image to the pane
        pane.getChildren().add(0, backgroundImage); // Add at the first position
    }

    // Used to set title "Wits & Wagers" for the whole game
    private void setMainTitle() {
        // Load the image
        ImageView mainTitleImage = new ImageView(new Image(getClass().getResource("/images/Asset 9.png").toExternalForm()));

        // Create an ImageView for the image

        mainTitleImage.setFitWidth(1000); // Set the desired width
        mainTitleImage.setPreserveRatio(true); // Preserve the aspect ratio

        // Position the ImageView at the top and center
        StackPane.setAlignment(mainTitleImage, Pos.TOP_CENTER);
        StackPane.setMargin(mainTitleImage, new Insets(60, 0, 0, 0)); // Add margin from the top

        // Add the ImageView to the main menu pane
        mainMenuPane.getChildren().add(mainTitleImage);

    }

    //Used to create the slots (betting mat)
    private void createBettingMat(Stage stage) {
        bettingMat.getChildren().clear();
        String[] buttonImagePaths = new String[]{
            getClass().getResource("/images/6to1.png").toExternalForm(),
            getClass().getResource("/images/5to1.png").toExternalForm(),
            getClass().getResource("/images/4to1.png").toExternalForm(),
            getClass().getResource("/images/3to1.png").toExternalForm(),
            getClass().getResource("/images/2to1.png").toExternalForm(),
            getClass().getResource("/images/3to1.png").toExternalForm(),
            getClass().getResource("/images/4to1.png").toExternalForm(),
            getClass().getResource("/images/5to1.png").toExternalForm()
        };
        bettingMat.setSpacing(15); // Set spacing between button groups
        bettingMat.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        for (int i = 0; i < 8; i++) {
            StackPane slotStackPane = new StackPane();
            slotStackPane.setPrefSize(138, 450); // Set preferred size to match the button
            slotStackPane.setMinSize(138, 450);  // Set minimum size to match the button
            slotStackPane.setMaxSize(138, 450);

            // Button
            Button button = new Button();
            ImageView buttonImage = new ImageView(new Image(buttonImagePaths[i]));
            buttonImage.setFitWidth(138); // Set the width of the image
            buttonImage.setFitHeight(450); // Set the height of the image

            slotStackPane.getChildren().add(buttonImage);
            if (i == 0) {
                button.setOnAction(e -> {
                    if (currentSecond > 0) {
                        game.getPlayer(playerCount - 1).setBetIdx(0);
                        showBetScreen(stage);
                    }
                });
            } else if (game.getValSlot(i) != -1) {
                Text text = new Text();
                text.setStyle("-fx-alignment: center; -fx-fill: white; -fx-font-size: 25px;"); // Increased font size
                text.setText(Long.toString(game.getValSlot(i)));
                text.setWrappingWidth(300);
                text.setTextAlignment(TextAlignment.CENTER);
                text.setRotate(-90);
                text.setStyle("-fx-fill: white; -fx-font-size: 45px; -fx-font-family: 'Comic Sans MS';");

                slotStackPane.getChildren().add(text);
                StackPane.setAlignment(text, Pos.CENTER);

                if (currentSecond > 0) {
                    int finalI = i;
                    button.setOnAction(e -> {
                        if (currentSecond > 0) {
                            game.getPlayer(playerCount - 1).setBetIdx(finalI);
                            showBetScreen(stage);
                        }
                    });
                }
            }

            // Set the StackPane as the graphic for the button
            button.setGraphic(slotStackPane);
            button.setAlignment(Pos.CENTER);
            button.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

            bettingMat.getChildren().add(button);
        }
    }

    //AMinly formating the UI elements wtih embedded CSS
    private void initialize(Stage stage ) {



        mainMenuPane.setPrefSize(1920, 1080);
//        mainMenuPane.setBackground("file:src/main/java/com/nhan/witsandwagers/images/Artboard 1.png");
        mainMenuPane.setStyle("-fx-background-color: #069f4a;");




        questionTitle.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ff8800;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        questionNumTitleOfShowGuessScreen.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ff8800;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


        bonusLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 150px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


        questionAnnoucementScreenLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        playerNameLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        playerBalanceLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


        StackPane.setAlignment(questionNumTitleOfShowGuessScreen, Pos.TOP_CENTER);
        StackPane.setMargin(questionNumTitleOfShowGuessScreen, new Insets(50, 0,0, 0)); // Optional: Adjust margin to control spacing


        StackPane.setAlignment(questionTitle, Pos.TOP_CENTER);
        StackPane.setMargin(questionTitle, new Insets(120, 0,0, 0)); // Optional: Adjust margin to control spacing


        requestPlayerLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-family: 'Comic Sans MS';");


        inputNameLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        betCountDownLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: gold; -fx-font-size: 130px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        correctAnswerLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        winningGuessLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        chosenSLotLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");



        nameField.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        nameField.setMaxWidth(400); // Explicitly set the maximum width

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Limit input length to 15 characters
            if (newValue.length() > 15) {
                nameField.setText(oldValue);
            }
        });


        nameField.setAlignment(Pos.CENTER);

        answerField.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        answerField.setMaxWidth(400); // Explicitly set the maximum width
        answerField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.length() > 10) {
                answerField.setText(oldValue);
            }
        });
        answerField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                answerField.setText(newValue.replaceAll("[^\\d]", "")); // Remove non-digit characters
            }
        });

        answerField.setAlignment(Pos.CENTER);
        notificationErrorLabel.setStyle("-fx-font-size: 35px; -fx-text-fill: #a60910; -fx-font-family: 'Comic Sans MS';");
        ImageView pauseIcon = new ImageView(new Image(getClass().getResource("/images/Asset 2.png").toExternalForm())); // Use the uploaded image path
        pauseIcon.setFitWidth(100); // Set the size of the icon
        pauseIcon.setFitHeight(100);
        pauseButton.setGraphic(pauseIcon);
        pauseButton.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
        pauseButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.1));
        pauseButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.1));

        StackPane.setAlignment(pauseButton, Pos.TOP_LEFT); // Position at the top-left corner
        StackPane.setMargin(pauseButton, new Insets(20, 0, 0, 20)); // Add margin for spacing


//        mainTitle.setStyle(
//                "-fx-text-fill: white;" +
//                        "-fx-font-size: 88px;" + // Font size
//                        "-fx-font-family: 'Comic Sans MS';" + // Font family
//                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
//                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
//        );
//
        readyLabel.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 80px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';" + // Font family
                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );

        stepLabel.setStyle(
                "-fx-text-fill: yellow;" +
                        "-fx-font-size: 65px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';" + // Font family
                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );


        contentLabel.setWrapText(true);
        contentLabel.setMaxWidth(1200);



        // Position the title at the top and center
//        StackPane.setAlignment(mainTitle, Pos.TOP_CENTER);
//        StackPane.setMargin(mainTitle, new Insets(50, 0, 0, 0)); // Add margin from the top

        winnersTitle.setStyle(
                "-fx-text-fill: #f8f403;" +
                        "-fx-font-size: 88px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';" + // Font family
                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );

        StackPane.setAlignment(winnersTitle, Pos.TOP_CENTER);
        StackPane.setMargin(winnersTitle, new Insets(50, 0, 0, 0)); // Add margin from the top


        note.setStyle(
                "-fx-text-fill: #a60910;" +
                        "-fx-font-size: 30px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';"  // Font family
//                       "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
//                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );

        note.setAlignment(Pos.CENTER);

        // Position the title at the top and center
        StackPane.setAlignment(note, Pos.BOTTOM_CENTER);
        StackPane.setMargin(note, new Insets(0, 0, 50, 0));

        hButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        vButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");
        StackPane.setAlignment(vButtonBox, Pos.CENTER);



//        backgroundImage.setImage(new Image("file:/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/mainMenu.jpg"));
//        backgroundImage.setPreserveRatio(true); // Preserve aspect ratio
//
//        // Bind image size to the window size
//        backgroundImage.fitWidthProperty().bind(mainMenuPane.widthProperty());
//        backgroundImage.fitHeightProperty().bind(mainMenuPane.heightProperty());

        playGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        exitGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        gameRuleButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        nextButton.setDefaultButton(true);
        nextButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.1));
        nextButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        nextButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        StackPane.setAlignment(nextButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(nextButton, new Insets(0, 100, 80, 0));

        backButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.1));
        backButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        StackPane.setAlignment(backButton, Pos.BOTTOM_LEFT);
        StackPane.setMargin(backButton, new Insets(0, 0, 80, 100));

        // Bind button sizes to window size
        playGameButton.setDefaultButton(true);
        playGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.2));
        playGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));


        exitGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.2));
        exitGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));


        gameRuleButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.2));
        gameRuleButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));


        homeButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        homeButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));

        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        StackPane.setAlignment(homeButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(homeButton, new Insets(0, 100, 80, 0));

    }


    @Override

    //Start scene (home scene)
    public void start(Stage primaryStage) {
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX(screenBounds.getMinX());
        primaryStage.setY(screenBounds.getMinY());
        primaryStage.setWidth(screenBounds.getWidth());
        primaryStage.setHeight(screenBounds.getHeight());

        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        setMainTitle();
        vButtonBox.getChildren().clear();
        vButtonBox.setSpacing(40);


        playGameButton.setText("Play Game");
        playGameButton.setOnAction(e -> {

            showPlayerSelectionScreen(primaryStage);
        });
        exitGameButton.setOnAction(e -> primaryStage.close());

        gameRuleButton.setOnAction(e -> {
            mainMenuPane.getChildren().clear();
            vButtonBox.getChildren().clear();
            vButtonBox.getChildren().addAll(stepLabel, contentLabel);
            mainMenuPane.getChildren().addAll(vButtonBox, backButton, nextButton);
            showRuleScreen(primaryStage);

        });


        vButtonBox.getChildren().addAll(playGameButton, exitGameButton, gameRuleButton);
        mainMenuPane.getChildren().addAll(vButtonBox);

        if (!gotBack) {
            gotBack = true;
            game.loadQuestions("Elements/questions.txt");
        }

        if (!gameStarted) {
            gameStarted = true;
            initialize(primaryStage);

            primaryStage.setResizable(false);
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Wits and Wagers");
            primaryStage.show();
        }
    }

    //Rules are displayed in this scene (method)
    private void showRuleScreen(Stage stage) {
        setBackgroundImage(mainMenuPane);
        setMainTitle();
        if (countStep == 0) {
            contentLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            stepLabel.setText("Overview");
            contentLabel.setText("Not a trivia buff? It doesn’t matter! Each player writes down a guess to the same " +
                    "question and places it face-up on the betting mat. Think you know it? " +
                    "Bet on your guess! Think you know who the expert is? Bet on them. The player with " +
                    "the most points after 7 questions wins.");
        } else if (countStep == 1) {
            contentLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            stepLabel.setText("When To Play");
            contentLabel.setText("Wits & Wagers is most fun with groups of 4-7 people. We recommend playing " +
                    "at holiday parties, family reunions, or any large gathering of friends. " +
                    "Be prepared for light-hearted banter, a little bravado, and a big dose of cheering!");
        } else if (countStep == 2) {
            contentLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            stepLabel.setText("Step 1: Read & Answer The Questions");
            contentLabel.setText("Each player will have 30 seconds to read, understand, and write down their answer (a guess) " +
                    "displayed on the screen. Please note, answers must be whole numbers only. Don’t be afraid to make a guess, " +
                    "because whether your answer is right or wrong doesn’t matter! The amount of money you earn or lose will depend " +
                    "on your bets during the betting round.");


        } else if (countStep == 3) {
            hButtonBox.getChildren().clear();
            stepLabel.setText("Step 2: Sort The Guesses");

            String text = "The predictions from each player will be automatically arranged in increasing order on a betting mat, " +
                    "from the smallest to the largest prediction, from left to right. If the total number of predictions is odd, " +
                    "the middle prediction will go into the 'Pay2to1' slot. If the total number of predictions is even, the 'Pay2to1'" +
                    " slot will stay empty. What about predictions that are the same? Don’t worry, just make your prediction because only" +
                    " one of the same predictions will be kept.";


            String part1 = text.substring(0, text.length() / 2);

            String part2 = text.substring(text.length() / 2);


            Label text1 = new Label(part1);
            text1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text1.setWrapText(true);
            text1.setMaxWidth(580);

            Label text2 = new Label(part2);
            text2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text2.setWrapText(true);
            text2.setMaxWidth(580);

            hButtonBox.setSpacing(50);

            hButtonBox.getChildren().addAll(text1, text2);


        } else if (countStep == 4) {
            hButtonBox.getChildren().clear();
            stepLabel.setText("Step 3: Bet");
            String text = "Each player will start the game with $70,000. After the 'Read & Answer The Question' round, each player will have 30 seconds to " +
                    "place a bet for once time per question. First, choose a slot to bet on (you can only bet on slots that have predictions in order). " +
                    "You can place your bet on any slot you like, even if it's not the one with your own prediction. During the " +
                    "30 seconds, you can change your bet as many times as you want. After that, you need to choose how much money (displayed on the screen) " +
                    "you want to bet, then it's the next player's turn. If you decide not to bet, that's fine— you won't lose " +
                    "anything, but you won't win anything either ^^. " +
                    "Also, each player can see their total money and the slot they've chosen on the screen.";

            String part1 = text.substring(0, text.length() / 3 + 12);

            String part2 = text.substring(text.length() / 3 + 12, (text.length() / 3) * 2 + 8);

            String part3 = text.substring((text.length() / 3) * 2 + 8);


            Label text1 = new Label(part1);
            text1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text1.setWrapText(true);
            text1.setMaxWidth(450);

            Label text2 = new Label(part2);
            text2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text2.setWrapText(true);
            text2.setMaxWidth(450);

            Label text3 = new Label(part3);
            text3.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text3.setWrapText(true);
            text3.setMaxWidth(450);

            hButtonBox.setSpacing(50);

            hButtonBox.getChildren().addAll(text1, text2, text3);

        } else if (countStep == 5) {
            hButtonBox.getChildren().clear();

            stepLabel.setText("Step 4: Determine The Wining Guess & Pay Bonus");
            String text = "After all players finish placing their bets, the bonus screen will be shown. " +
                    "If a player bets on a slot with a prediction that matches or is closest to the correct answer, " +
                    "that player will get back the amount they bet and will also receive extra money. The extra money is " +
                    "the amount they bet, multiplied by the number of slots (minus 1). For example, if a player bets $100 " +
                    "on the Pay3to1 slot and that slot has the correct answer, they will win $300. Also, if a player " +
                    "predicts the correct answer, they will get an additional $5000. Only one slot will win the prize," +
                    " and if a player bets on the other slots, they will lose their bet.";

            String part1 = text.substring(0, text.length() / 3 + 3);

            String part2 = text.substring(text.length() / 3 + 3, (text.length() / 3) * 2);

            String part3 = text.substring((text.length() / 3) * 2);


            Label text1 = new Label(part1);
            text1.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text1.setWrapText(true);
            text1.setMaxWidth(420);

            Label text2 = new Label(part2);
            text2.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text2.setWrapText(true);
            text2.setMaxWidth(420);

            Label text3 = new Label(part3);
            text3.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 28px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            text3.setWrapText(true);
            text3.setMaxWidth(420);

            hButtonBox.setSpacing(50);

            hButtonBox.getChildren().addAll(text1, text2, text3);


        } else if (countStep == 6) {

            contentLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            stepLabel.setText("Step 5: Determine Winners");
            contentLabel.setText("After 7 rounds of questions, the players with the most money will be the winners. Good luck!");
            homeButton.setOnAction(e -> {
                countStep = 0;
                start(stage);
            });
        }

        backButton.setOnAction(e -> {
            if (countStep == 0) {
                start(stage);
            } else if (countStep == 1) {
                countStep = 0;
                showRuleScreen(stage);
            } else if (countStep == 2) {
                countStep = 1;
                showRuleScreen(stage);
            } else if (countStep == 3) {
                countStep = 2;
                vButtonBox.getChildren().remove(hButtonBox);
                vButtonBox.getChildren().add(contentLabel);
                showRuleScreen(stage);
            } else if (countStep == 4) {
                countStep = 3;
                showRuleScreen(stage);
            } else if (countStep == 5) {
                countStep = 4;
                showRuleScreen(stage);
            } else if (countStep == 6) {
                vButtonBox.getChildren().remove(contentLabel);
                vButtonBox.getChildren().add(hButtonBox);

                countStep = 5;
                mainMenuPane.getChildren().remove(homeButton);
                mainMenuPane.getChildren().add(nextButton);
                showRuleScreen(stage);
            }
        });


        nextButton.setOnAction(e -> {
            if (countStep == 0) {
                countStep = 1;
                showRuleScreen(stage);
            } else if (countStep == 1) {
                countStep = 2;
                showRuleScreen(stage);
            } else if (countStep == 2) {
                vButtonBox.getChildren().remove(contentLabel);
                vButtonBox.getChildren().add(hButtonBox);
                countStep = 3;
                showRuleScreen(stage);
            } else if (countStep == 3) {
                countStep = 4;
                showRuleScreen(stage);
            } else if (countStep == 4) {
                countStep = 5;
                showRuleScreen(stage);
            } else if (countStep == 5) {
                vButtonBox.getChildren().add(contentLabel);
                vButtonBox.getChildren().remove(hButtonBox);
                countStep = 6;
                mainMenuPane.getChildren().remove(nextButton);
                mainMenuPane.getChildren().add(homeButton);
                showRuleScreen(stage);
            }

        });


    }

    //Scene for choosing numebr of player to play.
    private void showPlayerSelectionScreen( Stage stage) {


        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear() ;

        vButtonBox.setSpacing(30) ;


//        backgroundImage.setImage(new Image("file:player_select_bg.jpg"));


        backButton.setOnAction(e -> start(stage));
        backButton.setMinWidth(300);

        Button[] playerButtons = new Button[6];
        for (int i = 0; i < 6; i++) {

            playerButtons[i] = new Button("Play with " + (i +2)  + " players");

            // Bind button size to window size

            playerButtons[i].prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.22));
            playerButtons[i].prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
            playerButtons[i].setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            int finalI = i;
            playerButtons[i].setOnAction(e -> {
                numPlayers = finalI + 2 ;
                playerCount = 1 ;
                inputPlayersNameScreen(  stage);
            });
        }

        vButtonBox.getChildren().addAll(playerButtons);
        vButtonBox.getChildren().addAll(backButton) ;
        mainMenuPane.getChildren().addAll( vButtonBox);

    }
    private void inputPlayersNameScreen(Stage stage) {
        // Clear the current children of the main pane
        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        setMainTitle();
        nameField.clear() ;
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(10) ;



        hButtonBox.getChildren().clear() ;
        hButtonBox.setSpacing(200 );
        hButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        VBox localVBox = new VBox() ;

        localVBox.setSpacing(30);
        localVBox.setAlignment(Pos.CENTER);

        notificationErrorLabel.setText("");
        // Background image
//        backgroundImage.setImage(new Image("file:player_select_bg.jpg"));

        inputNameLabel.setText("Please enter a name for Player " + (playerCount ));

        nextButton.setMinWidth(200);
        nextButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                notificationErrorLabel.setText("Name cannot be empty. Please try again!");
            } else if (!game.isValidPlayerName(name)) { // Assume G
                notificationErrorLabel.setText("Name is invalid or already taken. Please enter a different name!");
            } else {
                game.addPlayer(name);
                if (playerCount < numPlayers) {
                    playerCount++;
                    inputPlayersNameScreen(  stage);
                } else {
                    notificationErrorLabel.setText("");
                    showReadyToPlayScreen( stage );
                }
            }
        });
        backButton.setMinWidth(120);
        backButton.setOnAction(e -> {

            if (playerCount > 1) {
                game.removePlayer();
                playerCount -- ;
                inputPlayersNameScreen(  stage);
            } else {
                showPlayerSelectionScreen( stage);
            }
        });

        hButtonBox.getChildren().addAll(backButton, nextButton);

        vButtonBox.getChildren().addAll( nameField, hButtonBox, notificationErrorLabel);
        localVBox.getChildren().addAll(inputNameLabel,vButtonBox) ;
        mainMenuPane.getChildren().addAll(localVBox);
    }

    //Scene to ask player "Are you ready? "
    private void showReadyToPlayScreen(Stage stage )  {
        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear() ;

//        backgroundImage.setImage(new Image("file:ready_to_play_bg.jpg"));
        vButtonBox.setSpacing(40) ;


        playGameButton.setText("Play") ;

        playGameButton.setOnAction(e -> showCountdownScreen( stage));


        backButton.setOnAction(e -> {

            game.removePlayer();
            inputPlayersNameScreen(  stage);
        });

        vButtonBox.getChildren().addAll(readyLabel, playGameButton, backButton);
        mainMenuPane.getChildren().addAll( vButtonBox);
    }

    //Scene for showing countdown before the game start
    private void showCountdownScreen( Stage stage) {
        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        countdown.getKeyFrames().clear();

//        backgroundImage.setImage(new Image("file:countdown_bg.jpg"));


        guessCountdownLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 200px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        mainMenuPane.getChildren().addAll(guessCountdownLabel);

        guessCountdownLabel.setText("3");
        guessCountdownLabel.setAlignment(null);
        StackPane.setAlignment(guessCountdownLabel, null);
        StackPane.setMargin(guessCountdownLabel, null);


        countdown.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), e -> {
                    int current = Integer.parseInt(guessCountdownLabel.getText());
                    if (current > 1) {
                        guessCountdownLabel.setText(String.valueOf(current - 1));
                    } else {

                        guessCountdownLabel.setText("Here we go!");
                        countdown.stop();
                        currentSecond = 20 ;
                        questionCount = 1 ;
                        playerCount = 1;
                        Timeline delay = new Timeline(
                                new KeyFrame(Duration.seconds(1), event -> showQuestionScreen( stage))
                        );
                        delay.play();
                    }
                })
        );

        countdown.setCycleCount(3);
        countdown.play();
    }

    //Scene for quesiton annoucement stage.
    private void showQuestionScreen(Stage stage ){

        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        questionAnnoucementScreenLabel.setText("Question " + questionCount);


        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            currentSecond = 30;
            showGuessScreen(stage);

        });
        mainMenuPane.getChildren().addAll(questionAnnoucementScreenLabel) ;
        pause.play();

    }

    //Scene to display questions and requires players to guess
    private void showGuessScreen(Stage stage) {

        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear();
        countdown.getKeyFrames().clear();
        vButtonBox.setSpacing(20);
        notificationErrorLabel.setText("");
        if(!paused) answerField.clear();
        paused = false ;

        questionNumTitleOfShowGuessScreen.setText("Question " + questionCount );
        questionTitle.setText(game.getQuestionElement(questionCount - 1).getQuestion());
        questionTitle.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ff8800;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);" +
                "-fx-wrap-text:true; -fx-alignment: center");
//

        StackPane.setAlignment(questionTitle, Pos.TOP_CENTER);
        StackPane.setMargin(questionTitle, new Insets(130, 50, 0, 50)); // Add margin from the top
        questionTitle.setMinHeight(Region.USE_PREF_SIZE);
        requestPlayerLabel.setText(game.getPlayer(playerCount - 1).getName ()  + ", let's try guessing a number!");
//        backgroundImage.setImage(new Image("file:question_bg.jpg"));
        if (!paused) {

            guessCountdownLabel.setText(Integer.toString(currentSecond));

            nextButton.setOnAction(e2 -> {
                if (currentSecond > 0) {
                    String currentGuessString = answerField.getText().trim();
                    // Check if the input is empty
                    if (currentGuessString.isEmpty()) {
                        notificationErrorLabel.setText("Your guess cannot be empty. Please try again!");

                    } else {

                        countdown.stop();
                        long guess = Long.parseLong(currentGuessString);
                        game.addGuess(guess);
                        game.getPlayer(playerCount - 1).setGuess(guess);

                        if (playerCount < numPlayers) {
                            playerCount++;
                            currentSecond = 30;
                            showGuessScreen(stage);
                        } else {
                            playerCount = 1;
                            game.sortGuesses();
                            game.placeGuessesInSlots();

                            currentSecond = 30;
                            createBettingMat(stage);

                            showTurnScreen(stage);

                        }
                    }
                }
            });


            String guessString;
            guessString = answerField.getText().trim();
            countdown.getKeyFrames().add(

                    new KeyFrame(Duration.seconds(1), e -> {

                        if (currentSecond > 0) {
                            guessCountdownLabel.setText(String.valueOf(--currentSecond));
                            if (currentSecond == 0) {
                                countdown.stop();
                                Timeline delay = new Timeline(
                                        new KeyFrame(Duration.seconds(1), event -> {
                                            if (guessString.isEmpty()) {
                                                game.addGuess(0);
                                                game.getPlayer(playerCount - 1).setGuess(0);
                                            } else {
                                                long guess = Long.parseLong(guessString);
                                                game.addGuess(guess);

                                            }

                                            if (playerCount < numPlayers) {
                                                playerCount++;
                                                currentSecond = 30;
                                                showGuessScreen(stage);
                                            } else {
                                                playerCount = 1;
                                                game.sortGuesses();
                                                game.placeGuessesInSlots();


                                                currentSecond = 30;
                                                createBettingMat(stage);

                                                showTurnScreen(stage);
                                            }
                                        })
                                );
                                delay.play();


                            }
//
                        }
                    })
            );
            countdown.setCycleCount(currentSecond);
            countdown.play();
        } else {
            paused = false;
            countdown.play();
        }

//
        pauseButton.setOnAction(e -> {
            countdown.stop() ;
            showPauseScreen(stage, 0  );
        });


        guessCountdownLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: gold; -fx-font-size: 130px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        guessCountdownLabel.setAlignment(Pos.CENTER);


        StackPane.setAlignment(guessCountdownLabel, Pos.BOTTOM_CENTER);
        StackPane.setMargin(guessCountdownLabel, new Insets(0, 0, 150, 0)); // Add margin from the top
//        StackPane.setAlignment(countdownLabel, Pos.TOP_CENTER);
//        StackPane.setMargin(countdownLabel, new Insets(180, 0, 0, 0));


        note.setText("Note: If you don't enter a guess within 30 seconds, your guess will automatically be set to 0.") ;
        StackPane.setAlignment(note, Pos.BOTTOM_CENTER);
        StackPane.setMargin(note, new Insets(0, 0, 130, 0)); // Add margin for spacing


        vButtonBox.getChildren().addAll(requestPlayerLabel, answerField, nextButton,notificationErrorLabel);
        StackPane.setAlignment(vButtonBox, Pos.CENTER);
        StackPane.setMargin(vButtonBox, new Insets(100, 0, 0, 0)); // Add margin for spacing
        mainMenuPane.getChildren().addAll(questionNumTitleOfShowGuessScreen, questionTitle, guessCountdownLabel, note, vButtonBox, pauseButton);


    }

    //Scene to inform who can bet
    private void showTurnScreen(Stage stage) {

        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(10);

        playerNameLabel.setText(game.getPlayer(playerCount -1  ).getName() ) ;

        questionAnnoucementScreenLabel.setText( "Now is your betting turn!" ) ;


        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            betScreenisShowed = false;
            game.getPlayer(playerCount - 1 ).setBetStatus(true );

            showTableScreen(stage);

        });

        vButtonBox.getChildren().addAll(playerNameLabel, questionAnnoucementScreenLabel);
        mainMenuPane.getChildren().addAll(vButtonBox) ;
        pause.play();


    }
    private void showPauseScreen(Stage stage, int chooseScreen) {
        mainMenuPane. getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        setMainTitle();
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(30 ) ;
        paused = true ;

        playGameButton.setText("Continue");

//        backgroundImage.setImage(new Image("file:question_bg.jpg"));

        homeButton.setOnAction(e ->{
            playerCount =1 ;
            paused = false;
            gotBack = false;
            game.clearGame() ;
            start(stage);
        });

        playGameButton.setOnAction(e-> {

            if(chooseScreen == 0 ){
                showGuessScreen(stage)  ;
            }
            if(chooseScreen == 1 ){
                showTableScreen(stage) ;
            }
            if(chooseScreen == 2 ){
                showBetScreen(stage);
            }
        }) ;

        vButtonBox.getChildren().addAll(playGameButton,homeButton, exitGameButton);
        mainMenuPane.getChildren().addAll(vButtonBox);

    }

    //Scene to show betting mat(slots). Players Can choose a valid slot to bet in this scene.
    private void showTableScreen(Stage stage) {

        StackPane.setAlignment(hButtonBox,Pos.BOTTOM_CENTER)  ;
        StackPane.setMargin(hButtonBox, new Insets(150,0, 0 ,0 ) ) ;

        if (!betScreenisShowed) {
            betScreenisShowed = true;
            countdown.getKeyFrames().clear();
            betCountDownLabel.setText(Integer.toString(currentSecond));

            mainMenuPane.getChildren().clear(); // Clear previous content
            setBackgroundImage(mainMenuPane);
            setMainTitle();
            countdown.getKeyFrames().add(

                    new KeyFrame(Duration.seconds(1), e -> {

                        if (currentSecond > 0) {
                            betCountDownLabel.setText(String.valueOf(--currentSecond));
                            if (currentSecond == 0) {
                                countdown.stop();
                                game.getPlayer(playerCount - 1).setBetStatus(false);

                                Timeline delay = new Timeline(
                                        new KeyFrame(Duration.seconds(1), event -> {
                                            if (playerCount < numPlayers) {
                                                playerCount++;
                                                currentSecond = 30;
                                                showTurnScreen(stage);
                                            } else {
                                                playerCount = 1;

                                                showBonusSummaryScreen(stage);
                                            }


                                        })
                                );
                                delay.play();


                            }
                        }
                    })
            );
            mainMenuPane.getChildren().addAll(betCountDownLabel, bettingMat,pauseButton);
            countdown.setCycleCount(currentSecond);
            countdown.play();


        } else if (paused) {

            mainMenuPane.getChildren().clear();
            setBackgroundImage(mainMenuPane);
            setMainTitle();
            mainMenuPane.getChildren().addAll(betCountDownLabel,  bettingMat,pauseButton);
            paused = false;
            countdown.play();


        } else {
            mainMenuPane.getChildren().removeAll(vButtonBox,pauseButton);
            mainMenuPane.getChildren().addAll(bettingMat,pauseButton);
        }



        StackPane.setAlignment(hButtonBox,Pos.BOTTOM_CENTER)  ;
        StackPane.setMargin(hButtonBox, new Insets(200,0, 0 ,0 ) ) ;

        StackPane.setAlignment(betCountDownLabel, Pos.TOP_RIGHT) ;
        StackPane.setMargin(betCountDownLabel, new Insets(30, 30, 0, 0)); // Add margin for spacing

        // Pause button action
        pauseButton.setOnAction(e -> {
            countdown.stop();
            showPauseScreen(stage, 1);
        });


    }

    //Scene to show players' balance and allow players to choose an amount of money to bet
    private void showBetScreen(Stage stage) {


        if (paused) {
            mainMenuPane.getChildren().clear();
            setBackgroundImage(mainMenuPane);
            setMainTitle();
            mainMenuPane.getChildren().addAll(betCountDownLabel, vButtonBox, pauseButton);
            paused = false;
            countdown.play();
        } else {
            mainMenuPane.getChildren().removeAll(bettingMat, pauseButton);
            mainMenuPane.getChildren().addAll(vButtonBox, pauseButton);
        }
        setBackgroundImage(mainMenuPane);
        hButtonBox.getChildren().clear() ;
        vButtonBox.getChildren().clear();
//        countdown.getKeyFrames().clear( );
        vButtonBox.setSpacing(60);
        hButtonBox.setSpacing(40);


//        betCountDownLabel.setText(Integer.toString(currentSecond) );
        playerBalanceLabel.setText("Your Current Balance: $" + game.getPlayer(playerCount - 1).getFund());

        long slotVal = game.getValSlot(game.getPlayer(playerCount -1 ).getBetIdx());
        if(slotVal != -1 ) {
            chosenSLotLabel.setText("Your Chosen Slot: \"" + slotVal + "\"");
        } else chosenSLotLabel.setText("Your Chosen Slot: \"ALL ANSWERS TOO HIGH\""
        );




        for( int i = 0 ; i < 5 ; i++){
            Button moneyButton = new Button() ;
            moneyButton.setText(buttonMoneyLabels[i]);
            moneyButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.150));
            moneyButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.000008));
            moneyButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #dc6606; -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
            int finalI = i;

            moneyButton.setOnAction(e -> {
                if (currentSecond > 0) {
                    countdown.stop();

                    if (finalI == 0) game.getPlayer(playerCount - 1).setBetAmounts(100);
                    else if (finalI == 1) game.getPlayer(playerCount - 1).setBetAmounts(500);
                    else if (finalI == 2) game.getPlayer(playerCount - 1).setBetAmounts(1000);
                    else if (finalI == 3) game.getPlayer(playerCount - 1).setBetAmounts(5000);
                    else if (finalI == 4) game.getPlayer(playerCount - 1).setBetAmounts(10000);

                    if (playerCount < numPlayers) {
                        playerCount++;
                        currentSecond = 30;

                        showTurnScreen(stage);
                    } else {
                        playerCount = 1;

                        showBonusSummaryScreen(stage);
                    }
                }

            });

            hButtonBox.getChildren().addAll(moneyButton);

        }



        pauseButton.setOnAction(e-> {
            countdown.stop() ;
            showPauseScreen(stage, 2  );
        }) ;

        backButton.setOnAction(e -> {
            showTableScreen(stage );

        });

        vButtonBox.getChildren().addAll( chosenSLotLabel, playerBalanceLabel,hButtonBox, backButton);
    }

    // Annoucement to the bonus scene
    private void showBonusSummaryScreen(Stage stage) {
        mainMenuPane.getChildren().clear();
        setBackgroundImage(mainMenuPane);


        pause.setDuration(Duration.seconds(2) );

        pause.setOnFinished(event -> {
            currentSecond = 4 + numPlayers ;
            showBonusDetailScreen( stage );
        });


        mainMenuPane.getChildren().addAll(bonusLabel) ;
        pause.play();

    }

    //Scene to show the bonus after each question round.
    private void showBonusDetailScreen(Stage stage ) {
        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear() ;
        countdown.getKeyFrames().clear() ;
        vButtonBox.setSpacing(35) ;
        correctAnswerLabel.setText("");
        winningGuessLabel.setText("");


        VBox newVBox = new VBox() ;

        newVBox.setSpacing(20) ;

        newVBox.setAlignment(Pos.CENTER);


        vButtonBox.getChildren().add(newVBox) ;

        newVBox.getChildren().addAll(correctAnswerLabel,winningGuessLabel) ;


        List<Label> bonusLabelList = new ArrayList<>() ;



        for(int i = 0 ; i < numPlayers; i++) {
            bonusLabelList.add(new Label());
            bonusLabelList.get(i).setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                    " -fx-font-size: 40px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


            vButtonBox.getChildren().add(bonusLabelList.get(i)) ;

        }


        int timeToShowAnswer = currentSecond -1 ;
        int timeToShowWinningGuess = currentSecond - 2 ;

        long correctAnswer = game.getQuestionElement(questionCount -1 ).getAnswer();
        Long winGuess = game.getWinGuess(correctAnswer );




        countdown.getKeyFrames().add(


                new KeyFrame(Duration.seconds(1), e -> {
                    currentSecond-- ;


                    if (currentSecond == timeToShowAnswer ) {
                        correctAnswerLabel.setText("Correct Answer: " + correctAnswer) ;
                    }
                    else if(currentSecond == timeToShowWinningGuess) {
                        if(winGuess == null )
                            winningGuessLabel.setText("All guesses are too high!" ) ;

                        else winningGuessLabel.setText("Winning Guess: " + winGuess) ;
                    }

                    else if (playerCount<= numPlayers) {
                        Player player = game.getPlayer(playerCount - 1 ) ;
                        if (!player.getBetStatus()  ) {

                            bonusLabelList.get((playerCount) - 1 ).setText(game.getPlayer(playerCount - 1 ).getName()
                                    + " did not make a guess and wins $0");

                        }
                        else if(!game.isWinSlot(player.getBetIdx(), game.getWinSlot(winGuess))) {
                            player.setFund(player.getFund() - player.getBetAmounts());

                            bonusLabelList.get((playerCount) - 1 ).setText(game.getPlayer(playerCount - 1 ).getName()
                                    + " loses $" + player.getBetAmounts());
                        }
                        else{


                            int bonus = game.calculateBonus(game.getWinSlot(winGuess), player.getBetAmounts());
                            player.setFund(player.getFund() + bonus - player.getBetAmounts());

                            bonusLabelList.get((playerCount) - 1 ).setText(game.getPlayer(playerCount - 1 ).getName()
                                    + " wins $" + bonus);

                        }

                        if(game.getPlayer(playerCount-1  ).getGuess() == correctAnswer  ) {
                            bonusLabelList.get(playerCount - 1).setText(bonusLabelList.get(playerCount - 1).getText() + " and gets $5000 for a correct guess");
                            player.setFund(player.getFund() + 5000);
                        }
                        playerCount++ ;

                    } else {

                        countdown.stop();

                        Timeline delay = new Timeline(
                                new KeyFrame(Duration.seconds(2), event -> {
                                    if(questionCount  < 7)  {
                                        playerCount =1 ;
                                        questionCount ++;
                                        game.clearGuesses();
                                        showQuestionScreen(stage)  ;
                                    }
                                    else{
                                        showResultScreen(stage ) ;

                                    }
                                })
                        );
                        delay.play();
                    }
                })
        );

        mainMenuPane.getChildren().add(vButtonBox) ;
        countdown.setCycleCount(currentSecond);
        countdown.play();

    }

    //Annoucement to the final result scene.
    private void showResultScreen(Stage stage ){
        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        questionAnnoucementScreenLabel.setText( "Final Result" ) ;
        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            showResultDetailScreen(stage);
        });
        mainMenuPane.getChildren().addAll(questionAnnoucementScreenLabel) ;
        pause.play();
    }

    //Scene to display the winners
    private void showResultDetailScreen(Stage stage){

        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        vButtonBox.getChildren().clear() ;
        countdown.getKeyFrames().clear() ;
        vButtonBox.setSpacing(30);


        List<Label> winnnersLalelList = new ArrayList<>();

        List<Player> winners = game.getWinners() ;



        for(int i = 0 ; i < winners.size() ; i ++){
            winnnersLalelList.add(new Label());
            winnnersLalelList.get(i).setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #f1de06;" +
                    " -fx-font-size: 60px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            vButtonBox.getChildren().add(winnnersLalelList.get(i)) ;
        }



        countdown.getKeyFrames().add(


                new KeyFrame(Duration.seconds(1), e -> {


                  if (winnersCount < winners.size()  ) {
                      winnnersLalelList.get(winnersCount).setText(winners.get(winnersCount).getName() + " is the winner with "
                                                                            + winners.get(winnersCount).getFund()+"$");

                      winnersCount++;

                    } else {

                        countdown.stop();

                        Timeline delay = new Timeline(
                                new KeyFrame(Duration.seconds(2), event -> {
                                    winnersCount = 0;
                                    showThankYouScreen(stage );

                                })
                        );
                        delay.play();


                    }
                })
        );
        mainMenuPane.getChildren().addAll(winnersTitle, vButtonBox);
        countdown.setCycleCount(winners.size() +  1 );
        countdown.play() ;
    }

    //Display thank you
    private void showThankYouScreen(Stage stage ){
        mainMenuPane.getChildren().clear() ;
        setBackgroundImage(mainMenuPane);
        setMainTitle();
        questionAnnoucementScreenLabel.setText( "Thank You!" ) ;

        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            gotBack = false;
            game.clearGame() ;
            start(stage);

        });

        mainMenuPane.getChildren().addAll(questionAnnoucementScreenLabel) ;
        pause.play();
    }

    //To launch the game.
    public static void main(String[] args) {
        launch(args);
    }
}

