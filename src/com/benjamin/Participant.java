package com.benjamin;

import java.util.List;

public class Participant {
    private String name;
    private double score;
    private List<Bet> onHold;
    private List<Bet> resolved;

    public Participant(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public double getScore() {
        return score;
    }
}
