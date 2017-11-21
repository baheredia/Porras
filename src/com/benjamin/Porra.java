package com.benjamin;

import java.util.ArrayList;
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
    private String result;

    private List<Bet> bets;

    public Porra(String name, String question, boolean isOpenQuestion, List<String> options) {
        this.name = name;
        this.state = State.ACTIVE;
        this.question = question;
        this.isOpenQuestion = isOpenQuestion;
        this.options = options;
        this.bets = new ArrayList<>();
    }
}
