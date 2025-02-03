package com.nhan.witsandwagers.Logic;

import com.nhan.witsandwagers.Elements.Player;
import com.nhan.witsandwagers.Elements.Question;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * Represents the main game logic for Wits and Wagers.
 */
public class Game {
    private final List<Player> players = new ArrayList<>();
    private final Set<Long> uniqueGuesses = new TreeSet<>();
    private List<Long> sortedUniqueGuesses = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final Long[] slots = new Long[8];
    private final List<Question> questions = new ArrayList<>();
    private List<Question> selectedQuestions = new ArrayList<>();
    private final List<Integer> usedQuestionIndices = new ArrayList<>();
    private final List<String> playerNames = new ArrayList<>();

    /**
     * Loads questions from a file.
     * @param filePath the path to the file containing questions
     */
    public void loadQuestions(String filePath) {
        if (questions.size() < 7) {
            questions.clear();
            URL resourceUrl = getClass().getResource("/" + filePath);
            if (resourceUrl == null) {
                System.err.println("Resource not found: " + filePath);
                System.exit(1);
            }
            try (InputStream inputStream = resourceUrl.openStream();
                 BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split("_");
                    if (parts.length == 3) {
                        questions.add(new Question(parts[0], Long.parseLong(parts[1]), Integer.parseInt(parts[2])));
                    }
                }
            } catch (IOException e) {
                System.err.println("Error reading the questions file: " + e.getMessage());
                System.exit(1);
            }
        }
        selectedQuestions = weightedRandomSampling();
    }

    /**
     * Selects a weighted random sample of questions.
     * @return a list of selected questions
     */
    public List<Question> weightedRandomSampling() {
        List<Question> selectedQuestions = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int totalWeight = questions.stream().mapToInt(Question::getWeight).sum();
            int randomValue = random.nextInt(totalWeight);
            int cumulativeWeight = 0;

            for (Question q : questions) {
                cumulativeWeight += q.getWeight();
                if (randomValue < cumulativeWeight) {
                    selectedQuestions.add(q);
                    questions.remove(q);
                    break;
                }
            }
        }
        return selectedQuestions;
    }

    /**
     * Gets a question element by index.
     * @param index the index of the question
     * @return the question at the specified index
     */
    public Question getQuestionElement(int index) {
        return this.selectedQuestions.get(index);
    }

    /**
     * Checks if a player name is valid.
     * @param name the name to check
     * @return true if the name is valid, false otherwise
     */
    public boolean isValidPlayerName(String name) {
        if (!name.isEmpty() && !playerNames.contains(name)) {
            playerNames.add(name);
            return true;
        }
        return false;
    }

    /**
     * Adds a player to the game.
     * @param name the name of the player
     */
    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    /**
     * Removes the last player from the game.
     */
    public void removePlayer() {
        players.remove(players.size() - 1);
        playerNames.remove(playerNames.size() - 1);
    }

    /**
     * Clears the game state.
     */
    public void clearGame() {
        selectedQuestions.clear();
        players.clear();
        playerNames.clear();
        usedQuestionIndices.clear();
        uniqueGuesses.clear();
        slots.clone();
        sortedUniqueGuesses.clear();
    }

    /**
     * Adds a guess to the set of unique guesses.
     * @param guess the guess to add
     */
    public void addGuess(long guess) {
        uniqueGuesses.add(guess);
    }

    /**
     * Sorts the unique guesses.
     */
    public void sortGuesses() {
        sortedUniqueGuesses = new ArrayList<>(uniqueGuesses);
    }

    /**
     * Clears the unique guesses.
     */
    public void clearGuesses() {
        uniqueGuesses.clear();
    }

    /**
     * Places guesses into the 8 slots around slot 4.
     */
    public void placeGuessesInSlots() {
        Arrays.fill(slots, (long) -1);

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

    /**
     * Asks a question and gets guesses from players.
     * @param questionNumber the number of the question
     */
    public void askQuestion(int questionNumber) {
        System.out.println("\nQuestion " + questionNumber + ": " + selectedQuestions.get(questionNumber - 1) + "\n");

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

    /**
     * Gets the value of a slot by index.
     * @param idx the index of the slot
     * @return the value of the slot
     */
    public long getValSlot(int idx) {
        return this.slots[idx];
    }

    /**
     * Gets a player by index.
     * @param idx the index of the player
     * @return the player at the specified index
     */
    public Player getPlayer(int idx) {
        return this.players.get(idx);
    }

    /**
     * Displays the slots.
     */
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

    /**
     * Allows players to place bets.
     */
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

    /**
     * Calculates the bonus based on the bet index and bet amounts.
     * @param betIdx the index of the bet
     * @param betAmounts the amount of the bet
     * @return the calculated bonus
     */
    public int calculateBonus(int betIdx, int betAmounts) {
        if (betIdx == 0)
            return betAmounts * 6;
        else
            return (Math.abs(betIdx - 4) + 2) * betAmounts;
    }

    /**
     * Determines the winning guess based on the correct answer.
     * @param correctAnswer the correct answer
     * @return the winning guess
     */
    public Long getWinGuess(long correctAnswer) {
        Long winningGuess = null;
        for (Long guess : sortedUniqueGuesses) {
            if (guess <= correctAnswer) {
                winningGuess = guess;
            } else {
                break;
            }
        }
        return winningGuess;
    }

    /**
     * Gets the winning slot based on the winning guess.
     * @param winningGuess the winning guess
     * @return the index of the winning slot
     */
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

    /**
     * Checks if a slot is the winning slot.
     * @param slot the slot to check
     * @param winningSlot the winning slot
     * @return true if the slot is the winning slot, false otherwise
     */
    public boolean isWinSlot(int slot, int winningSlot) {
        return slot == winningSlot;
    }

    /**
     * Determines the winners based on the correct answer.
     * @param correctAnswer the correct answer
     */
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

    /**
     * Displays the current scores of all players.
     */
    public void displayScores() {
        System.out.println("\nCurrent Scores:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getFund() + " points");
        }
    }

    /**
     * Gets the list of winners.
     * @return the list of players with the highest score
     */
    public List<Player> getWinners() {
        int maxFund = players.stream().mapToInt(Player::getFund).max().orElse(0);

        List<Player> winners = new ArrayList<>();
        for (Player player : players) {
            if (player.getFund() == maxFund) {
                winners.add(player);
            }
        }
        return winners;
    }
}