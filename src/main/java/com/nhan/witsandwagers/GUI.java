package com.nhan.witsandwagers;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class GUI extends Application {
    private boolean mainMenuStatus = false;

    // Main menu layout
    private StackPane mainMenuPane = new StackPane();

    // Scene
    private Scene mainMenuScene = new Scene(mainMenuPane);

    // Backend Game instance
    private Game game = new Game();

    // Background image
//    private ImageView backgroundImage = new ImageView();



    // Buttons
    private Button nextButton = new Button("Next");

    private Button backButton = new Button("Back");
    //Box
    private VBox vButtonBox = new VBox();


    private HBox hButtonBox = new HBox() ; // To arrange buttons horizontally

    private Label questionNumTitle  = new Label( ) ;
    private Label questionTitle = new Label() ;
    private VBox vTitleBox =new VBox() ;
    private Label requestPlayerLabel = new Label() ;

    private Label nameLabel = new Label();
    private TextField nameField = new TextField();

    private TextField answerField = new TextField() ;

    private int numPlayers;

    private Label notificationLabel = new Label();

    private Button pauseButton = new Button();

    private Button playGameButton = new Button();

    private Button exitGameButton = new Button("Exit Game");
    private Label mainTitle = new Label("WITS & WAGERS");

    private int questionCount = 1 ;
    private int playerCount = 1 ;

    private Label countdownLabel = new Label();
    // Declare and initialize the Timeline
    private Timeline countdown = new Timeline();

    private Label note = new Label() ;

    private int currentSecond ;
    private Boolean paused  ;



    @Override
    public void start(Stage primaryStage) {
        mainMenuPane.getChildren().clear();
        vButtonBox.getChildren().clear();
        vButtonBox.setSpacing(40);
        mainMenuPane.setPrefSize(1920, 1080);
        mainMenuPane.setStyle("-fx-background-color: #069f4a;");

        paused= false ;

        playGameButton.setText("Play Game") ;

        questionTitle.setStyle(
                "-fx-text-fill: darkorange;" +
                        "-fx-font-size: 50px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';"  // Font family
//                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
//                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );

        questionNumTitle.setStyle(
                "-fx-text-fill: darkorange;" +
                        "-fx-font-size: 50px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';"  // Font family
//                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
//                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );

//        questionNumTitle.setAlignment(Pos.CENTER);
//        questionTitle.setAlignment(Pos.CENTER);


        StackPane.setAlignment(questionNumTitle, Pos.TOP_CENTER);
        StackPane.setMargin(questionNumTitle, new Insets(50, 0,0, 0)); // Optional: Adjust margin to control spacing


        StackPane.setAlignment(questionTitle, Pos.TOP_CENTER);
        StackPane.setMargin(questionTitle, new Insets(120, 0,0, 0)); // Optional: Adjust margin to control spacing


        requestPlayerLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-family: 'Comic Sans MS';");


        nameLabel.setStyle("-fx-font-size: 30px; -fx-text-fill: #ffffff; -fx-font-family: 'Comic Sans MS';");


        nameField.setStyle("-fx-font-size: 20px; -fx-text-fill: black;");
        nameField.setMaxWidth(400); // Explicitly set the maximum width
        nameField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (" ".equals(event.getCharacter())) {
                event.consume(); // Prevent the space character from being added to the TextField
            }
        });

        nameField.textProperty().addListener((observable, oldValue, newValue) -> {
            // Limit input length to 25 characters
            if (newValue.length() > 25) {
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


        notificationLabel.setStyle("-fx-font-size: 18px; -fx-text-fill: #a60910; -fx-font-family: 'Comic Sans MS';");



        ImageView pauseIcon = new ImageView(new Image("file:/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/pause.png")); // Use the uploaded image path
        pauseIcon.setFitWidth(150); // Set the size of the icon
        pauseIcon.setFitHeight(150);
        pauseButton.setGraphic(pauseIcon);
        pauseButton.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
        pauseButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.1));
        pauseButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.1));

        StackPane.setAlignment(pauseButton, Pos.TOP_LEFT); // Position at the top-left corner
        StackPane.setMargin(pauseButton, new Insets(30, 0, 0, 30)); // Add margin for spacing

        // Create the title

        mainTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 88px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';" + // Font family
                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );
//        mainTitle.setAlignment(Pos.CENTER); // Center-align the title text


        // Position the title at the top and center
        StackPane.setAlignment(mainTitle, Pos.TOP_CENTER);
        StackPane.setMargin(mainTitle, new Insets(50, 0, 0, 0)); // Add margin from the top


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


        vButtonBox.getChildren().addAll(playGameButton, exitGameButton);
        vButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");
        StackPane.setAlignment(vButtonBox, Pos.CENTER);

        vTitleBox.setSpacing(20);

//        backgroundImage.setImage(new Image("file:/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/mainMenu.jpg"));
//        backgroundImage.setPreserveRatio(true); // Preserve aspect ratio
//
//        // Bind image size to the window size
//        backgroundImage.fitWidthProperty().bind(mainMenuPane.widthProperty());
//        backgroundImage.fitHeightProperty().bind(mainMenuPane.heightProperty());

        playGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        exitGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        playGameButton.setOnAction(e -> showPlayerSelectionScreen(primaryStage));
        exitGameButton.setOnAction(e -> primaryStage.close());

        nextButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.07));
        nextButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        nextButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        backButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.07));
        backButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        backButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        // Bind button sizes to window size
        playGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        playGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        exitGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.14));
        exitGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));

        // Add components to the main menu pane

        mainMenuPane.getChildren().addAll( mainTitle, vButtonBox);

        if (!mainMenuStatus) {
            game.loadQuestions("/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/questions.txt");
            game.selectUniqueQuestions();
            primaryStage.setScene(mainMenuScene);
            primaryStage.setTitle("Wits and Wagers");
            primaryStage.show();
        }
    }


    private void showPlayerSelectionScreen( Stage stage) {

        // Clear the current children of the main pane
        mainMenuPane.getChildren().clear();
        vButtonBox.getChildren().clear() ;

        vButtonBox.setSpacing(50) ;

        // Background image
//        backgroundImage.setImage(new Image("file:player_select_bg.jpg"));


        backButton.setOnAction(e -> start(stage));

        Button[] playerButtons = new Button[6];
        for (int i = 0; i < 6; i++) {

            playerButtons[i] = new Button("Play with " + (i +2)  + " players");

            // Bind button size to window size
            playerButtons[i].prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.185));
            playerButtons[i].prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
            playerButtons[i].setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

            int finalI = i;
            playerButtons[i].setOnAction(e -> {
                numPlayers = finalI + 2 ;
                inputPlayersName(  stage);
            });
        }

        vButtonBox.getChildren().addAll(playerButtons);
        vButtonBox.getChildren().addAll(backButton) ;


        mainMenuPane.getChildren().addAll( vButtonBox);
        mainMenuStatus = true;
    }

    private void inputPlayersName( Stage stage) {
        // Clear the current children of the main pane
        mainMenuPane.getChildren().clear();
        nameField.clear() ;
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(10) ;

        hButtonBox.getChildren().clear() ;
        hButtonBox.setSpacing(200 );
        hButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        notificationLabel.setText("");
        // Background image
//        backgroundImage.setImage(new Image("file:player_select_bg.jpg"));

        nameLabel.setText("Please enter a name for Player " + (playerCount ));

        nextButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                notificationLabel.setText("Name cannot be empty. Please try again!");
            } else if (!game.isValidPlayerName(name)) { // Assume G
                notificationLabel.setText("Name is invalid or already taken. Please enter a different name!");
            } else {
                game.addPlayer(name);
                if (playerCount < numPlayers) {
                    playerCount++;
                    inputPlayersName(  stage);
                } else {
                    showReadyToPlayScreen( stage );
                }
            }
        });

        backButton.setOnAction(e -> {

            if (playerCount > 1) {
                game.removePlayer();
                playerCount -- ;
                inputPlayersName(  stage);
            } else {
                showPlayerSelectionScreen( stage);
            }
        });

        hButtonBox.getChildren().addAll(backButton, nextButton);

        vButtonBox.getChildren().addAll(nameLabel, nameField, hButtonBox, notificationLabel);
        mainMenuPane.getChildren().addAll( mainTitle ,vButtonBox);
    }

    private void showReadyToPlayScreen(Stage stage )  {
        mainMenuPane.getChildren().clear();
        vButtonBox.getChildren().clear() ;

//        backgroundImage.setImage(new Image("file:ready_to_play_bg.jpg"));
        vButtonBox.setSpacing(20) ;


        Label readyLabel = new Label("Are you ready to play ?" );
        readyLabel.setStyle("-fx-font-size: 60px; -fx-text-fill: #caa510; -fx-font-family: 'Comic Sans MS';");


        playGameButton.setText("Play") ;
        playGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        playGameButton.setOnAction(e -> showCountdownScreen( stage));


        backButton.setOnAction(e -> {

            game.removePlayer();
            inputPlayersName(  stage);
        });

        vButtonBox.getChildren().addAll(readyLabel, playGameButton, backButton);
        mainMenuPane.getChildren().addAll( vButtonBox);
    }


    private void showCountdownScreen( Stage stage) {
        mainMenuPane.getChildren().clear();
        countdown.getKeyFrames().clear();

//        backgroundImage.setImage(new Image("file:countdown_bg.jpg"));


        countdownLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 200px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        mainMenuPane.getChildren().addAll( countdownLabel);

        countdownLabel.setText("3");
        countdownLabel.setAlignment(null);
        StackPane.setAlignment(countdownLabel, null);
        StackPane.setMargin(countdownLabel, null);


        countdown.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), e -> {
                    int current = Integer.parseInt(countdownLabel.getText());
                    if (current > 1) {
                        countdownLabel.setText(String.valueOf(current - 1));
                    } else {

                        countdownLabel.setText("Here we go!");
                        countdown.stop();
                        currentSecond = 20 ;
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
    private void showQuestionScreen(Stage stage) {

        mainMenuPane.getChildren().clear();
        vButtonBox.getChildren().clear();
        countdown.getKeyFrames().clear();
        vButtonBox.setSpacing(20);
        notificationLabel.setText("");

        if(!paused) answerField.clear();

        paused = false ;

        questionNumTitle.setText("Question " + questionCount );
        questionTitle.setText(game.getQuestion(questionCount - 1));
        requestPlayerLabel.setText(game.getPlayerName(playerCount - 1) + ", let's try guessing a number!");
//        backgroundImage.setImage(new Image("file:question_bg.jpg"));

        countdownLabel.setText(Integer.toString(currentSecond) );

        String guessString;
        guessString = answerField.getText().trim();
        countdown.getKeyFrames().add(

                new KeyFrame(Duration.seconds(1), e -> {
                    int current = Integer.parseInt(countdownLabel.getText());
                    if (current >1) {
                        countdownLabel.setText(String.valueOf(current - 1));
                        nextButton.setOnAction(e2 -> {
                            String currentGuessString = answerField.getText().trim();

                            // Check if the input is empty
                            if (currentGuessString.isEmpty()) {
                                notificationLabel.setText("Your guess cannot be empty. Please try again!");

                            }

                            else {
                                countdown.stop() ;
                                long guess = Long.parseLong(currentGuessString); // Parse the input as a number
                                game.addGuess(guess);

                                if (playerCount < numPlayers) {
                                    playerCount++;
                                    currentSecond  = 20 ;
                                    showQuestionScreen(stage); // Move to the next player's turn
                                } else {
                                    playerCount = 1 ;
                                    game.sortGuesses();
                                    game.placeGuessesInSlots();
                                    currentSecond = 20 ;
                                    showTableScreen(stage); // Proceed to the next screen

                                }
                            }
                        });
                    } else {

                        countdownLabel.setText("0");
                        countdown.stop();
                        Timeline delay = new Timeline(
                                new KeyFrame(Duration.seconds(1), event -> {
                                    if(guessString.isEmpty()){
                                        game.addGuess(0);
                                    }
                                    else {
                                        long guess = Long.parseLong(guessString);
                                        game.addGuess(guess) ;

                                    }

                                    if(playerCount < numPlayers) {
                                        playerCount++;
                                        currentSecond = 20 ;
                                        showQuestionScreen(stage);
                                    }
                                    else {
                                        playerCount = 1 ;
                                        game.sortGuesses();
                                        game.placeGuessesInSlots();
                                        currentSecond = 20 ;
                                        showTableScreen(stage);
                                    }
                                })
                        );
                        delay.play();
                    }
                })
        );

//        // Next Button Logic
//        nextButton.setOnAction(e -> {
//            String currentGuessString = answerField.getText().trim();
//
//            // Check if the input is empty
//            if (currentGuessString.isEmpty()) {
//                notificationLabel.setText("Your guess cannot be empty. Please try again!");
//
//            }
//
//            else {
//                countdown.stop() ;
//                long guess = Long.parseLong(currentGuessString); // Parse the input as a number
//                game.addGuess(guess);
//
//                if (playerCount < numPlayers) {
//                    playerCount++;
//                    currentSecond  = 20 ;
//                    showQuestionScreen(stage); // Move to the next player's turn
//                } else {
//                    playerCount = 1 ;
//                    game.sortGuesses();
//                    game.placeGuessesInSlots();
//                    currentSecond = 20 ;
//                    showTableScreen(stage); // Proceed to the next screen
//
//                }
//            }
//        });
        pauseButton.setOnAction(e -> {

            countdown.stop() ;
            currentSecond = Integer.parseInt(countdownLabel.getText());
            showPauseScreen(stage, 0  );
        });




        countdownLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: gold; -fx-font-size: 130px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        countdownLabel.setAlignment(Pos.CENTER);

        StackPane.setAlignment(countdownLabel, Pos.TOP_CENTER);
        StackPane.setMargin(countdownLabel, new Insets(180, 0, 0, 0)); // Add margin from the top


        note.setText("Note: If you don't enter a guess within 20 seconds, your guess will automatically be set to 0.") ;



        vButtonBox.getChildren().addAll(requestPlayerLabel, answerField, nextButton,notificationLabel);
        mainMenuPane.getChildren().addAll( questionNumTitle, questionTitle,countdownLabel, note, vButtonBox,pauseButton);

        countdown.setCycleCount(20);
        countdown.play();
    }



    private void showPauseScreen(Stage stage, int chooseScreen) {
        mainMenuPane. getChildren().clear() ;
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(30 ) ;
        paused = true ;

        playGameButton.setText("Continue");

//        backgroundImage.setImage(new Image("file:question_bg.jpg"));
        Button homeButton = new Button("Home") ;
        homeButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        homeButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));

        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        homeButton.setOnAction(e ->{
            playerCount =1 ;
            mainMenuStatus = false;
            game.clearGame() ;
            start(stage);
        });

        playGameButton.setOnAction(e-> {

            if(chooseScreen == 0 ){
                showQuestionScreen(stage)  ;
            }
            if(chooseScreen == 1 ){
                showTableScreen(stage) ;
            }
        }) ;

        vButtonBox.getChildren().addAll(playGameButton,homeButton, exitGameButton);
        mainMenuPane.getChildren().addAll( mainTitle, vButtonBox);

    }
    private void showTableScreen(Stage stage) {
        mainMenuPane.getChildren().clear(); // Clear previous content
        hButtonBox.getChildren().clear();   // Clear horizontal layout
        hButtonBox.setSpacing(10);          // Set spacing between button groups
        hButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        StackPane.setAlignment(hButtonBox,Pos.BOTTOM_CENTER)  ;
        StackPane.setMargin(hButtonBox, new Insets(200,0, 0 ,0 ) ) ;
        // Pause button action
        pauseButton.setOnAction(e -> {
            countdown.stop();
            currentSecond = Integer.parseInt(countdownLabel.getText());
            showPauseScreen(stage, 1);
        });

        String[] buttonLabels = {
                "6to1",
                "5to1",
                "4to1",
                "3to1",
                "2to1",
                "3to1",
                "4to1",
                "5to1"
        };

        for (int i = 0 ; i < 8 ; i ++) {
            // Create a VBox for each button group

            VBox vButtonBox = new VBox();
            vButtonBox.setSpacing(5); // Space between text and button

            // Top text
            Text text1 = new Text(buttonLabels[i]);
            text1.setStyle("-fx-fill: white; -fx-font-size: 18px; -fx-font-family: 'Comic Sans MS';");

            // Button
            Button button = new Button();


//            if(game.getValSlot(i) != -1  ){
//                String valSlotStr = Long.toString(game.getValSlot(i) );
//
//                button.setText( valSlotStr ) ;
//
//
//            }
            button.setPrefWidth(120);
            button.setPrefHeight(600);
            button.setStyle(
                    "-fx-background-color: transparent;" + // Transparent body
                            "-fx-border-color: white;" +           // White border
                            "-fx-border-width: 3;" +              // Border thickness
                            "-fx-text-fill: white;" +             // White text
                            "-fx-font-size: 14px;" +              // Font size for better readability
                            "-fx-border-radius: 20;" +            // Rounded border corners
                            "-fx-padding: 10;"                    // Padding inside button
            );
            // Bottom text (rotated)
            Text text2 = new Text(buttonLabels[i]);
            text2.setStyle("-fx-fill: white; -fx-font-size: 18px; -fx-font-family: 'Comic Sans MS';");
            text2.setRotate(180);

            // Add text and button to VBox
            vButtonBox.getChildren().addAll(text1, button, text2);

            // Add VBox to horizontal box
            hButtonBox.getChildren().add(vButtonBox);
        }

        // Add components to the main pane
        mainMenuPane.getChildren().addAll(mainTitle, hButtonBox, pauseButton);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
