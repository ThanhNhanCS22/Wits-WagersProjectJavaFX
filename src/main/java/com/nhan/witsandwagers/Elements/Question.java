package com.nhan.witsandwagers.Elements;

        /**
         * Represents a question in the Wits and Wagers game.
         */
        public class Question {
            private final String question;
            private final long answer;
            private final int weight;

            /**
             * Constructs a new Question with the specified question text, answer, and weight.
             * @param question the text of the question
             * @param answer the answer to the question
             * @param weight the weight of the question
             */
            public Question(String question, long answer, int weight) {
                this.question = question;
                this.answer = answer;
                this.weight = weight;
            }

            /**
             * Gets the text of the question.
             * @return the text of the question
             */
            public String getQuestion() {
                return this.question;
            }

            /**
             * Gets the answer to the question.
             * @return the answer to the question
             */
            public long getAnswer() {
                return this.answer;
            }

            /**
             * Gets the weight of the question.
             * @return the weight of the question
             */
            public int getWeight() {
                return this.weight;
            }
        }