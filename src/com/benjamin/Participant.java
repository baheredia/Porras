package com.benjamin;

import java.util.ArrayList;
import java.util.List;

public class Participant {
    private String name;
    private double score;
    private int pointsBetted;
    private List<Bet> onHold;
    private List<Bet> resolved;

    public Participant(String name, double score) {
        this.name = name;
        this.score = score;
        this.pointsBetted = 0;
        this.onHold = new ArrayList<>();
        this.resolved = new ArrayList<>();
    }

    public int getBettingPoints () {
        return (int) score - pointsBetted;
    }

    public boolean betPoints(int points) {
        if(this.getBettingPoints()>= points) {
            this.pointsBetted+=points;
            return true;
        } else {
            return false;
        }

    }

    public void resolveBet(Bet bet, double scale) {
        // scale is a positive number if the bet was won, and
        // -1 if it was lost
        // First recover the points
        this.pointsBetted -= bet.getBet();
        // Modify the score
        this.score += bet.getBet()*scale;
        this.onHold.remove(bet);
        this.resolved.add(bet);

    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }

    public List<Bet> getOnHold() {
        return onHold;
    }

    public List<Bet> getResolved() {
        return resolved;
    }
}
