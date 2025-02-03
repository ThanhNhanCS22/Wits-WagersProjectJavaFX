package com.nhan.witsandwagers.Elements;

    /**
     * Represents a player in the Wits and Wagers game.
     */
    public class Player {
        private String name;
        private int fund;
        private int betIdx;
        private int betAmounts;
        private boolean betStatus;
        private long guess;

        /**
         * Constructs a new Player with the specified name.
         * @param name the name of the player
         */
        public Player(String name) {
            this.name = name;
            this.fund = 70000;
            this.betStatus = true;
        }

        /**
         * Sets the player's guess.
         * @param guess the guess to set
         */
        public void setGuess(long guess) {
            this.guess = guess;
        }

        /**
         * Gets the player's guess.
         * @return the player's guess
         */
        public long getGuess() {
            return this.guess;
        }

        /**
         * Sets the player's bet status.
         * @param status the bet status to set
         */
        public void setBetStatus(boolean status) {
            this.betStatus = status;
        }

        /**
         * Gets the player's bet status.
         * @return the player's bet status
         */
        public boolean getBetStatus() {
            return this.betStatus;
        }

        /**
         * Gets the player's name.
         * @return the player's name
         */
        public String getName() {
            return this.name;
        }

        /**
         * Sets the player's fund.
         * @param fund the fund to set
         */
        public void setFund(int fund) {
            this.fund = fund;
        }

        /**
         * Gets the player's fund.
         * @return the player's fund
         */
        public int getFund() {
            return this.fund;
        }

        /**
         * Sets the player's bet index.
         * @param betIdx the bet index to set
         */
        public void setBetIdx(int betIdx) {
            this.betIdx = betIdx;
        }

        /**
         * Gets the player's bet index.
         * @return the player's bet index
         */
        public int getBetIdx() {
            return this.betIdx;
        }

        /**
         * Sets the player's bet amounts.
         * @param betAmounts the bet amounts to set
         */
        public void setBetAmounts(int betAmounts) {
            this.betAmounts = betAmounts;
        }

        /**
         * Gets the player's bet amounts.
         * @return the player's bet amounts
         */
        public int getBetAmounts() {
            return this.betAmounts;
        }
    }