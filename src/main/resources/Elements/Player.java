package com.nhan.witsandwagers.Elements;

public class Player {
    private String name;
    private int fund;

    private int betIdx;
    private int betAmounts;
    private boolean betStatus;
    private long guess ;

    public Player(String name) {
        this.name = name;
        this.fund = 70000;
        this.betStatus = true;
    }

    public void setGuess(long guess ) {
        this.guess = guess ;
    }
    public long getGuess() {
        return this.guess ;
    }

    public void  setBetStatus(boolean status ) {
        this.betStatus = status ;
    }

    public boolean getBetStatus() {
        return this.betStatus ;
    }


    public String getName() {
        return this.name;
    }

    public void setFund(int fund) {
        this.fund = fund;
    }

    public int getFund() {
        return this.fund;
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
