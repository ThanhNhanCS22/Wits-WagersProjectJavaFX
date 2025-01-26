package com.nhan.witsandwagers.Gui;

import com.nhan.witsandwagers.Elements.Player;
import com.nhan.witsandwagers.Logic.Game;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;




public class GUI extends Application {


    private boolean betScreenisShowed = false;

    private final Button gameRuleButton = new Button("How To Play");

    private final StackPane mainMenuPane = new StackPane();

    private final Scene mainMenuScene = new Scene(mainMenuPane);

    private final Game game = new Game();

    private final Button nextButton = new Button("Next");

    private final Button backButton = new Button("Back");
    //Box
    private final VBox vButtonBox = new VBox();

    private final HBox hButtonBox = new HBox() ; // To arrange buttons horizontally

    private final Label questionNumTitleOfShowGuessScreen = new Label( ) ;

    private final Label questionTitle = new Label() ;

    private final VBox vTitleBox =new VBox() ;

    private final Label requestPlayerLabel = new Label() ;

    private final Label inputNameLabel = new Label();

    private final TextField nameField = new TextField();

    private final TextField answerField = new TextField() ;

    private int numPlayers;

    private final Label notificationLabel = new Label();

    private final Button pauseButton = new Button();

    private final Button playGameButton = new Button();

    private final Button exitGameButton = new Button("Exit Game");
    private final Label readyLabel = new Label("Are you ready?");

    private final Label mainTitle = new Label("WITS & WAGERS");

    private final Label winnersTitle = new Label("WINNER(S)") ;

    private int questionCount ;

    private int playerCount ;

    private final Label guessCountdownLabel = new Label();

    private final Label betCountDownLabel = new Label();

    private final Timeline countdown = new Timeline();

    private final Label note = new Label() ;

    private int currentSecond ;

    private Boolean paused = false;

    private final Label waitingScreenLabel = new Label() ;

    private final Label playerNameLabel= new Label() ;

    private final Label playerBalanceLabel = new Label() ;

    private final Label bonusLabel = new Label() ;

    private final PauseTransition pause = new PauseTransition();

    private final Label correctAnswerLabel = new Label() ;

    private final Label winningGuessLabel = new Label() ;
    private final Label stepLabel = new Label();

    private String[] buttonSlotLabels ;

    private final Label chosenSLotLabel = new Label() ;

    private int winnersCount  = 0 ;

    private boolean gameStarted = false ;
    private final Label contentLabel = new Label();
    Button homeButton = new Button("Home");
    private boolean gotBack = false;
    private int countStep = 0;

    private void initialize(Stage stage ) {

        mainMenuPane.setPrefSize(1920, 1080);

        mainMenuPane.setStyle("-fx-background-color: #069f4a;");


        questionTitle.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ff8800;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

        questionNumTitleOfShowGuessScreen.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ff8800;" +
                " -fx-font-size: 50px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


        bonusLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff;" +
                " -fx-font-size: 150px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");


        waitingScreenLabel.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 80px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

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
            // Limit input length to 25 characters
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
        notificationLabel.setStyle("-fx-font-size: 35px; -fx-text-fill: #a60910; -fx-font-family: 'Comic Sans MS';");
        ImageView pauseIcon = new ImageView(new Image("file:/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/images/pause.png")); // Use the uploaded image path
        pauseIcon.setFitWidth(150); // Set the size of the icon
        pauseIcon.setFitHeight(150);
        pauseButton.setGraphic(pauseIcon);
        pauseButton.setStyle("-fx-background-color: transparent;"); // Make the button background transparent
        pauseButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.1));
        pauseButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.1));

        StackPane.setAlignment(pauseButton, Pos.TOP_LEFT); // Position at the top-left corner
        StackPane.setMargin(pauseButton, new Insets(30, 0, 0, 30)); // Add margin for spacing


        mainTitle.setStyle(
                "-fx-text-fill: white;" +
                        "-fx-font-size: 88px;" + // Font size
                        "-fx-font-family: 'Comic Sans MS';" + // Font family
                        "-fx-effect: dropshadow(gaussian, black, 8, 0.7, 2, 2)," + // Outer black shadow
                        "dropshadow(gaussian, red, 8, 0.7, -2, -2);" // Inner red shadow for border effect
        );
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
        StackPane.setAlignment(mainTitle, Pos.TOP_CENTER);
        StackPane.setMargin(mainTitle, new Insets(50, 0, 0, 0)); // Add margin from the top

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

        vTitleBox.setSpacing(20);

//        backgroundImage.setImage(new Image("file:/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/mainMenu.jpg"));
//        backgroundImage.setPreserveRatio(true); // Preserve aspect ratio
//
//        // Bind image size to the window size
//        backgroundImage.fitWidthProperty().bind(mainMenuPane.widthProperty());
//        backgroundImage.fitHeightProperty().bind(mainMenuPane.heightProperty());

        playGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        exitGameButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        gameRuleButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");

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
        playGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        playGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        exitGameButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.14));
        exitGameButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));
        gameRuleButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        gameRuleButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));


        homeButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.15));
        homeButton.prefHeightProperty().bind(mainMenuPane.heightProperty().multiply(0.0008));

        homeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-text-fill: #ffffff; -fx-font-size: 35px; -fx-font-family: 'Comic Sans MS'; -fx-effect: dropshadow(gaussian, black, 4, 0.5, 1, 1);");
        StackPane.setAlignment(homeButton, Pos.BOTTOM_RIGHT);
        StackPane.setMargin(homeButton, new Insets(0, 100, 80, 0));



    }


    @Override
    public void start(Stage primaryStage) {
        mainMenuPane.getChildren().clear();
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
            mainMenuPane.getChildren().addAll(mainTitle, vButtonBox, backButton, nextButton);
            showRuleScreen(primaryStage);

        });


        vButtonBox.getChildren().addAll(playGameButton, exitGameButton, gameRuleButton);
        mainMenuPane.getChildren().addAll(mainTitle, vButtonBox);

        if (!gotBack) {
            gotBack = true;
            game.loadQuestions("/Users/thanhnhan/Desktop/javaProject/witsAndWagers/src/main/java/com/nhan/witsandwagers/Elements/questions.txt");
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

    private void showRuleScreen(Stage stage) {


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
            contentLabel.setText("Wits & Wagers is most fun with groups of maximum 7 people. We recommend playing " +
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
        nameField.clear() ;
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(10) ;



        hButtonBox.getChildren().clear() ;
        hButtonBox.setSpacing(200 );
        hButtonBox.setStyle("-fx-alignment: center; -fx-background-color: transparent;");

        VBox localVBox = new VBox() ;

        localVBox.setSpacing(30);
        localVBox.setAlignment(Pos.CENTER);

        notificationLabel.setText("");
        // Background image
//        backgroundImage.setImage(new Image("file:player_select_bg.jpg"));

        inputNameLabel.setText("Please enter a name for Player " + (playerCount ));

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
                    inputPlayersNameScreen(  stage);
                } else {
                    notificationLabel.setText("");
                    showReadyToPlayScreen( stage );
                }
            }
        });

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

        vButtonBox.getChildren().addAll( nameField, hButtonBox, notificationLabel);
        localVBox.getChildren().addAll(inputNameLabel,vButtonBox) ;
        mainMenuPane.getChildren().addAll( mainTitle ,localVBox);
    }
    private void showReadyToPlayScreen(Stage stage )  {
        mainMenuPane.getChildren().clear();
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
    private void showCountdownScreen( Stage stage) {
        mainMenuPane.getChildren().clear();
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
    private void showQuestionScreen(Stage stage ){

        mainMenuPane.getChildren().clear() ;

        waitingScreenLabel.setText("Question " + questionCount);


        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            currentSecond = 30;
            showGuessScreen(stage);

        });
        mainMenuPane.getChildren().addAll(waitingScreenLabel) ;
        pause.play();

    }
    private void showGuessScreen(Stage stage) {

        mainMenuPane.getChildren().clear();
        vButtonBox.getChildren().clear();
        countdown.getKeyFrames().clear();
        vButtonBox.setSpacing(20);
        notificationLabel.setText("");
        if(!paused) answerField.clear();
        paused = false ;

        questionNumTitleOfShowGuessScreen.setText("Question " + questionCount );
        questionTitle.setText(game.getQuestionElement(questionCount - 1).getQuestion());
        requestPlayerLabel.setText(game.getPlayer(playerCount - 1).getName ()  + ", let's try guessing a number!");
//        backgroundImage.setImage(new Image("file:question_bg.jpg"));
        if (!paused) {

            guessCountdownLabel.setText(Integer.toString(currentSecond));

            nextButton.setOnAction(e2 -> {
                if (currentSecond > 0) {
                    String currentGuessString = answerField.getText().trim();
                    // Check if the input is empty
                    if (currentGuessString.isEmpty()) {
                        notificationLabel.setText("Your guess cannot be empty. Please try again!");

                    } else {

                        countdown.stop();
                        long guess = Long.parseLong(currentGuessString); // Parse the input as a number
                        game.addGuess(guess);
                        game.getPlayer(playerCount - 1).setGuess(guess);

                        if (playerCount < numPlayers) {
                            playerCount++;
                            currentSecond = 30;
                            showGuessScreen(stage); // Move to the next player's turn
                        } else {
                            playerCount = 1;
                            game.sortGuesses();
                            game.placeGuessesInSlots();
                            currentSecond = 30;
                            showTurnScreen(stage); // Proceed to the next screen

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

        StackPane.setAlignment(guessCountdownLabel, Pos.TOP_CENTER);
        StackPane.setMargin(guessCountdownLabel, new Insets(180, 0, 0, 0)); // Add margin from the top


        note.setText("Note: If you don't enter a guess within 20 seconds, your guess will automatically be set to 0.") ;



        vButtonBox.getChildren().addAll(requestPlayerLabel, answerField, nextButton,notificationLabel);
        mainMenuPane.getChildren().addAll(questionNumTitleOfShowGuessScreen, questionTitle, guessCountdownLabel, note, vButtonBox, pauseButton);


    }
    private void showTurnScreen(Stage stage) {

        mainMenuPane.getChildren().clear() ;
        vButtonBox.getChildren().clear() ;
        vButtonBox.setSpacing(10);

        playerNameLabel.setText(game.getPlayer(playerCount -1  ).getName() ) ;

        waitingScreenLabel.setText( "Now is your betting turn!" ) ;


        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            betScreenisShowed = false;
            game.getPlayer(playerCount - 1 ).setBetStatus(true );

            showTableScreen(stage);

        });

        vButtonBox.getChildren().addAll(playerNameLabel, waitingScreenLabel);
        mainMenuPane.getChildren().addAll(vButtonBox) ;
        pause.play();


    }
    private void showPauseScreen(Stage stage, int chooseScreen) {
        mainMenuPane. getChildren().clear() ;
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
        mainMenuPane.getChildren().addAll( mainTitle, vButtonBox);

    }
    private void showTableScreen(Stage stage) {
        hButtonBox.getChildren().clear();
        hButtonBox.setSpacing(15);


        if (!betScreenisShowed) {
            betScreenisShowed = true;
            countdown.getKeyFrames().clear();
            betCountDownLabel.setText(Integer.toString(currentSecond));

            mainMenuPane.getChildren().clear(); // Clear previous content

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
            countdown.setCycleCount(currentSecond);
            countdown.play();
            mainMenuPane.getChildren().addAll(mainTitle, betCountDownLabel, pauseButton, hButtonBox);

        } else if (paused) {

            mainMenuPane.getChildren().clear();
            mainMenuPane.getChildren().addAll(mainTitle, betCountDownLabel, pauseButton, hButtonBox);
            paused = false;
            countdown.play();


        } else {
            mainMenuPane.getChildren().remove(vButtonBox);
            mainMenuPane.getChildren().add(hButtonBox);
        }



        StackPane.setAlignment(hButtonBox,Pos.BOTTOM_CENTER)  ;
        StackPane.setMargin(hButtonBox, new Insets(200,0, 0 ,0 ) ) ;

        StackPane.setAlignment(betCountDownLabel, Pos.TOP_RIGHT) ;
        StackPane.setMargin(betCountDownLabel, new Insets(30, 100, 0, 0)); // Add margin for spacing

        // Pause button action
        pauseButton.setOnAction(e -> {
            countdown.stop();
            showPauseScreen(stage, 1);
        });




        buttonSlotLabels = new String[]{
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
            Text text1 = new Text(buttonSlotLabels[i]);
            if(i == 0 ) text1.setStyle("-fx-aligment: center;-fx-fill: #f6e406; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS';");
            else text1.setStyle("-fx-aligment: center;-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS';");

            text1.setRotate(180);
            // Button
            Button button = new Button();


            if (i == 0) {
                Text text3 = new Text();
                text3.setText("ALL ANSWERS TOO HIGH");
                text3.setStyle("-fx-alignment: center; -fx-fill: blue; -fx-font-size: 25px;"); // Increased font size
                text3.setRotate(-90);
                text3.setWrappingWidth(160); // Wrap the text within a specific width

                button.setGraphic(text3);

                button.setOnAction(e -> {
                    if (currentSecond > 0) {
                        game.getPlayer(playerCount - 1).setBetIdx(0);
                        showBetScreen(stage);
                    }

                });

            } else if (game.getValSlot(i) != -1) {
                Text text3 = new Text();
                text3.setStyle("-fx-alignment: center; -fx-fill: white; -fx-font-size: 25px;"); // Increased font size
                text3.setText(Long.toString(game.getValSlot(i)));
                text3.setRotate(-90);
                text3.setWrappingWidth(160); // Wrap the text within a specific width

                button.setGraphic(text3);
                if(currentSecond > 0 ) {
                    int finalI = i;
                    button.setOnAction(e -> {
                        if (currentSecond > 0) {
                            game.getPlayer(playerCount - 1).setBetIdx(finalI);
                            showBetScreen(stage);
                        }

                    });
                }
            }

            button.setPrefWidth(180); // Fixed width
            button.setPrefHeight(500); // Fixed height

            button.setMaxWidth(180); // Maximum width
            button.setMaxHeight(500); // Maximum height


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
            Text text2 = new Text(buttonSlotLabels[i]);
            if (i == 0 )text2.setStyle("-fx-aligment: center;-fx-fill: #f6e406; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS';");
            else text2.setStyle("-fx-aligment: center;-fx-fill: white; -fx-font-size: 30px; -fx-font-family: 'Comic Sans MS';");

            // Add text and button to VBox
            vButtonBox.setAlignment(Pos.CENTER) ;
            vButtonBox.getChildren().addAll(text1, button, text2);

            // Add VBox to horizontal box
            hButtonBox.getChildren().add(vButtonBox);
        }

    }
    private void showBetScreen(Stage stage) {


        if (paused) {
            mainMenuPane.getChildren().clear();
            mainMenuPane.getChildren().addAll(mainTitle, betCountDownLabel, vButtonBox, pauseButton);
            paused = false;
            countdown.play();
        } else {
            mainMenuPane.getChildren().removeAll(hButtonBox, pauseButton);
            mainMenuPane.getChildren().addAll(vButtonBox, pauseButton);
        }
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


        String[] buttonMoneyLabels = {
                "$100",
                "$500",
                "$1000",
                "$5000",
                "$10000"
        };


        for( int i = 0 ; i < 5 ; i++){
            Button moneyButton = new Button() ;
            moneyButton.setText(buttonMoneyLabels[i]);
            moneyButton.prefWidthProperty().bind(mainMenuPane.widthProperty().multiply(0.118));
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
    private void showBonusSummaryScreen(Stage stage) {
        mainMenuPane.getChildren().clear();

        bonusLabel.setText("Bonus Summary") ;

        pause.setDuration(Duration.seconds(2) );

        pause.setOnFinished(event -> {
            currentSecond = 4 + numPlayers ;
            showBonusDetailScreen( stage );
        });


        mainMenuPane.getChildren().addAll(bonusLabel) ;
        pause.play();

    }
    private void showBonusDetailScreen(Stage stage ) {
        mainMenuPane.getChildren().clear() ;
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
    private void showResultScreen(Stage stage ){
        mainMenuPane.getChildren().clear() ;
        waitingScreenLabel.setText( "Final Result" ) ;
        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            showResultDetailScreen(stage);
        });
        mainMenuPane.getChildren().addAll(waitingScreenLabel) ;
        pause.play();
    }
    private void showResultDetailScreen(Stage stage){

        mainMenuPane.getChildren().clear() ;
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
    private void showThankYouScreen(Stage stage ){
        mainMenuPane.getChildren().clear() ;
        waitingScreenLabel.setText( "Thank You!" ) ;

        pause.setDuration(Duration.seconds(2));
        pause.setOnFinished(event -> {
            gotBack = false;
            game.clearGame() ;
            start(stage);

        });

        mainMenuPane.getChildren().addAll(waitingScreenLabel) ;
        pause.play();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
