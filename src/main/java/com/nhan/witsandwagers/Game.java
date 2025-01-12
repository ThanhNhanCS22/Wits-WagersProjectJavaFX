package com.nhan.witsandwagers;
import java.io.*;
import java.util.*;

// Represents the main game logic
public class Game {
    private List<Player> players = new ArrayList<>();
    private Set<Long> uniqueGuesses = new TreeSet<>();
    private List<Long> sortedUniqueGuesses = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);
    private Long[] slots = new Long[8];
    private List<String> questions = new ArrayList<>();
    private List<Integer> answers = new ArrayList<>();
    private List<Integer> usedQuestionIndices = new ArrayList<>();
    private List<String> playerNames = new ArrayList<>();

    // Load questions from a file
    public void loadQuestions(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("_");
                if (parts.length == 2) {
                    questions.add(parts[0]);
                    answers.add(Integer.parseInt(parts[1]));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the questions file: " + e.getMessage());
            System.exit(1);
        }
    }

    public void selectUniqueQuestions() {
        Random random = new Random();
        usedQuestionIndices.clear(); // Clear previously used indices to avoid conflicts in multiple games

        while (usedQuestionIndices.size() <= 7) {
            int index = random.nextInt(questions.size());
            if (!usedQuestionIndices.contains(index))
                usedQuestionIndices.add(index);

        }

        List<String> selectedQuestions = new ArrayList<>();
        List<Integer> selectedAnswers = new ArrayList<>();

        for (int index : usedQuestionIndices) {
            selectedQuestions.add(questions.get(index));
            selectedAnswers.add(answers.get(index));
        }

        this.questions = selectedQuestions;
        this.answers = selectedAnswers;
    }

    public String getQuestion(int index) {
        return this.questions.get(index) ;
    }

    public String getPlayerName(int index ){
        return this.players.get(index).getName() ;
    }

    public void setPlayerBetAmount(int idx, int amount ) {
        this.players.get(idx).setBetAmounts(amount);
    }


    public boolean isValidPlayerName(String name) {
        if (!name.isEmpty() && !playerNames.contains(name)) {
            playerNames.add(name);

            return true ;
        }
        return false ;

    }
    public void addPlayer(String name){
        players.add(new Player(name))  ;

    }
    public void removePlayer(){

        players.remove(players.size() - 1);
        playerNames.remove(playerNames.size() - 1);
    }

    public void clearGame() {
        questions.clear() ;
        players.clear() ;
        playerNames.clear() ;
        answers.clear() ;
        usedQuestionIndices.clear() ;
        uniqueGuesses.clear();
        slots.clone() ;
        sortedUniqueGuesses.clear() ;

    }

    public void addGuess(long guess) {
        uniqueGuesses.add(guess);
    }
    public void sortGuesses (){
        sortedUniqueGuesses = new ArrayList<>(uniqueGuesses);
    }

    // Place guesses into the 8 slots around slot 4
    public void placeGuessesInSlots() {
        Arrays.fill(slots, (long)-1);
        slots[0] = (long) -1; // Slot 0 represents "All Guesses Too High"

        int size = sortedUniqueGuesses.size();

        int idx = 0;
        if (size % 2 == 0) {

            for (int i = 3 - size / 2 + 1; i < 4; i++) {
                slots[i] = sortedUniqueGuesses.get(idx++);
            }
            for (int i = 5; idx < size; i++) {
                slots[i] = sortedUniqueGuesses.get(idx++);
            }
        } else {
            for (int i = 3 - size / 2 + 1; idx < size; i++) {
                slots[i] = sortedUniqueGuesses.get(idx++);
            }
        }
    }



    // Ask a question and get guesses from players
    public void askQuestion(int questionNumber) {
        System.out.println("\nQuestion " + questionNumber + ": " + questions.get(questionNumber - 1) + "\n");

        uniqueGuesses.clear();


        for (Player player : players) {
            while (true) {
                System.out.print(player.getName() + ", enter your guess: ");
                String input = scanner.nextLine().trim();
                try {
                    long guess = Integer.parseInt(input);
                    if (guess < 0) {
                        System.out.println("Only accept natural number. Please enter again.");

                        continue;
                    }


                    uniqueGuesses.add(guess);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid guess. Please enter again.");
                }
            }
        }

        sortedUniqueGuesses = new ArrayList<>(uniqueGuesses);
        placeGuessesInSlots();
    }


    public long getValSlot(int idx){
        return this.slots[idx] ;
    }

    public Player getPlayer(int idx) {
        return this.players.get(idx) ;
    }

    public long getAnswer(int questionIdx) {
        return answers.get(questionIdx) ;


    }

    // Display the slots
    public void displaySlots() {
        System.out.println("\nSlots:");
        for (int i = 0; i < slots.length; i++) {
            if (i == 0) {
                System.out.println("Slot 0: All Guesses Too High");
            } else {
                System.out.println("Slot " + i + ": " + (slots[i] != null ? slots[i] : "[Empty]"));
            }
        }
    }

    // Place bets
    public void placeBets() {
        System.out.println("\nTime to place your bets!\n");
        for (Player player : players) {

            while (true) {

                System.out.print(player.getName() + ", please choose a valid slot (");

                int check = 0;
                for (int idx = 0; idx < 8; idx++) {

                    if (slots[idx] != null) {
                        if (check == 1)
                            System.out.print("-");
                        else
                            check = 1;
                        System.out.printf("%d", idx);

                    }

                }
                System.out.print("): ");
                try {

                    int betIdxInt = Integer.parseInt(scanner.nextLine().trim());
                    if (betIdxInt < 0) {
                        System.out.println("Invalid slot. Please enter again.");
                        continue;
                    }
                    player.setBetIdx(betIdxInt);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid slot. Please enter again.");
                    continue;
                }

                if (player.getBetIdx() >= 0 && player.getBetIdx() < slots.length
                        && slots[player.getBetIdx()] != null) {
                    while (true) {
                        try {

                            System.out.print(player.getName()
                                    + ", please enter the amount to bet (maximum 10 points) on slot "
                                    + player.getBetIdx() + ": ");
                            int AmountsInt = Integer.parseInt(scanner.nextLine().trim());
                            if (AmountsInt < 0 || AmountsInt > 10)
                                System.out.println("Invalid betting points. Please enter again.");

                            else {
                                player.setBetAmounts(AmountsInt);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid betting points. Please enter again.");

                        }
                    }
                    System.out.println();
                    break;
                } else {
                    System.out.println("Invalid slot. Please choose a slot that has a guess in it.");
                }
            }

        }
    }

    public int calculateBonus(int betIdx, int betAmounts) {
        if (betIdx == 0)
            return betAmounts * 6;
        else
            return (Math.abs(betIdx - 4) + 2) * betAmounts;

    }

    // Determine the winning guess based on the correct answer

    public Long getWinGuess (long correctAnswer) {
        Long winningGuess = null ;
        for (Long guess : sortedUniqueGuesses) {
            if (guess <= correctAnswer) {
                winningGuess = guess;
            } else {
                break;
            }
        }
        return winningGuess;
    }

    public int getWinSlot(Long winningGuess) {

        int winningSlot = -1;
        if (winningGuess == null) {

            winningSlot = 0;
        } else {
            for (int i = 0; i < slots.length; i++) {
                if (slots[i] != null && slots[i].equals(winningGuess)) {
                    winningSlot = i;
                    break;
                }
            }


        }

        return winningSlot;
    }

    public boolean isWinSlot (int slot ,int winningSlot  ){
        if(slot == winningSlot) return true ;
        return false ;
    }
    public void determineWinners(int correctAnswer) {
        System.out.println("\nThe correct answer is: " + correctAnswer);

        Long winningGuess = null;
        for (Long guess : sortedUniqueGuesses) {
            if (guess <= correctAnswer) {
                winningGuess = guess;
            } else {
                break;
            }
        }

        int winningSlot = -1;
        if (winningGuess == null) {
            System.out.println("All guesses are too high! Slot 0 wins.");
            winningSlot = 0;
        } else {
            for (int i = 0; i < slots.length; i++) {
                if (slots[i] != null && slots[i].equals(winningGuess)) {
                    winningSlot = i;
                    break;
                }
            }
            System.out.println("\nWinning guess: " + winningGuess + "! Slot " + winningSlot + " wins!\n");

        }

        for (Player player : players) {

            if (player.getBetIdx() == winningSlot) {
                int bonus = calculateBonus(winningSlot, player.getBetAmounts());
                System.out.println(player.getName() + " wins and gets " + bonus + " points!");
                player.setFund(player.getFund() + bonus);

            } else {
                System.out.println(player.getName() + " loses " + player.getBetAmounts() + " points!");
                player.setFund(player.getFund() - player.getBetAmounts());

            }
        }
    }

    // Display scores
    public void displayScores() {
        System.out.println("\nCurrent Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getFund() + " points");
        }
    }

    // Play the full game
//    public void playGame() {
//        loadQuestions("questions.txt");
//        selectUniqueQuestions();
//        initializePlayers();
//
//        for (int round = 1; round <= 7; round++) {
//            askQuestion(round);
//            displaySlots();
//            placeBets();
//
//            int correctAnswer = answers.get(round - 1);
//            determineWinners(correctAnswer);
//            displayScores();
//        }
//
//        // Determine the final winner
//        int maxPoints = players.stream().mapToInt(p -> p.getPoints()).max().orElse(0);
//
//        List<Player> winners = new ArrayList<>();
//        for (Player player : players) {
//            if (player.getPoints() == maxPoints) {
//                winners.add(player);
//            }
//        }
//
//        if (!winners.isEmpty()) {
//            System.out.print("\nThe winner(s): ");
//            for (int i = 0; i < winners.size(); i++) {
//                System.out.print(winners.get(i).getName());
//                if (i < winners.size() - 1) {
//                    System.out.print(", ");
//                }
//            }
//            System.out.println(" with " + maxPoints + " points!");
//        }
//
//    }

}
