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
