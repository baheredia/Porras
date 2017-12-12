package com.benjamin;

public class Bet {
    private Participant participant;
    private String porraName;
    private String option;
    private int bet;

    public Bet(Participant participant, String porraName, String option, int bet) {
        this.participant = participant;
        this.porraName = porraName;
        this.option = option;
        this.bet = bet;
    }

    public Participant getParticipant() {
        return participant;
    }

    public String getPorra() {
        return porraName;
    }

    public String getOption() {
        return option;
    }

    public int getBet() {
        return bet;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
