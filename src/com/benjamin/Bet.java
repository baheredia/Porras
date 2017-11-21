package com.benjamin;

public class Bet {
    private Participant participant;
    private Porra porra;
    private String option;
    private int bet;

    public Bet(Participant participant, Porra porra, String option, int bet) {
        this.participant = participant;
        this.porra = porra;
        this.option = option;
        this.bet = bet;
    }

    public Participant getParticipant() {
        return participant;
    }

    public Porra getPorra() {
        return porra;
    }

    public String getOption() {
        return option;
    }

    public int getBet() {
        return bet;
    }
}
