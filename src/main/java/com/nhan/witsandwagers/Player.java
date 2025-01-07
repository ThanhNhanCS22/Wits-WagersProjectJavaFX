package com.nhan.witsandwagers;

public class Player {
    private String name;
    private int points;

    private int betIdx;
    private int betAmounts;

    protected Player(String name) {
        this.name = name;
        this.points = 70;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getPoints() {
        return this.points;
    }


    public void setBetIdx(int betIdx) {
        this.betIdx = betIdx;
    }

    public int getBetIdx() {
        return this.betIdx;
    }

    public void setBetAmounts(int betAmounts) {
        this.betAmounts = betAmounts;
    }

    public int getBetAmounts() {
        return this.betAmounts;
    }

}
