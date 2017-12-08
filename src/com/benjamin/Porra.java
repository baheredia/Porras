package com.benjamin;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Porra {
    private String name;

    public enum State {
        ACTIVE, // You can bet on this
        WAITING_RESULT, // You can not bet, but the result is still unknown
        RESOLVED // The result is known (you can not bet anymore)
    }

    private State state;
    private String question;
    private boolean isOpenQuestion; // False if the list of options is final
    private List<String> options;
    private LocalDate resolutionDate;
    private String result;

    private List<Bet> bets;

    public Porra(String name, String question, boolean isOpenQuestion,
                 LocalDate resolutionDate, List<String> options) {
        this.name = name;
        this.state = State.ACTIVE;
        this.question = question;
        this.isOpenQuestion = isOpenQuestion;
        this.resolutionDate = resolutionDate;
        this.options = options;
        this.bets = new ArrayList<>();
    }

    public boolean placeBet(Participant participant, String option, int points) {
        if(state == State.ACTIVE) {
            if (options.contains(option)) {
                if (participant.betPoints(points)) {
                    Bet bet = new Bet(participant, this, option, points);
                    bets.add(bet);
                    participant.getOnHold().add(bet);
                    return true;
                } else {
                    return false;
                }
            } else if(isOpenQuestion) {
                options.add(option);
                if (participant.betPoints(points)) {
                    Bet bet = new Bet(participant, this, option, points);
                    bets.add(bet);
                    participant.getOnHold().add(bet);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public List<Participant> winners() {
        return null;
    }

    public List<Participant> losers () {
        return null;
    }

    public String getName() {
        return name;
    }

    public State getState() {
        return state;
    }

    public String getQuestion() {
        return question;
    }

    public boolean isOpenQuestion() {
        return isOpenQuestion;
    }

    public List<String> getOptions() {
        return options;
    }

    public String getResult() {
        return result;
    }

    public List<Bet> getBets() {
        return bets;
    }

    public void setState(State state) {
        this.state = state;
    }
}
