package com.nhan.witsandwagers.Elements;

public class Question {
    private final String question ;
    private final long answer ;
    private final int weight ;


    public Question(String question, long answer, int weight ) {
        this.question =  question ;
        this.answer = answer ;
        this.weight = weight ;
    }

    public String getQuestion() {
        return this.question ;
    }

    public long getAnswer() {
        return this.answer ;
    }
    public int getWeight() {
        return this.weight ;
    }
}
