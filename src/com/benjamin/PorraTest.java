package com.benjamin;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PorraTest {

    Participant participant1;
    Participant participant2;
    Porra porra1;
    Porra porra2;
    Porra passedPorra;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        participant1 = new Participant("Ben", 200);
        participant2 = new Participant("Mari", 200);
        List<String> options = new ArrayList<>();
        options.add("yes");
        options.add("no");
        porra1 = new Porra("ex", "Is the sun yellow?", false,
                LocalDate.of(2018, Month.JANUARY,15),
                options);
        porra2 = new Porra("ex2", "What?", true,
                LocalDate.of(2018,Month.APRIL,1),
                options);
        passedPorra = new Porra("ex3","What day was yesterday?",
                false, LocalDate.of(2017,Month.DECEMBER,7),
                options);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_is_nice() {
        boolean placed = porra1.placeBet(participant1,"yes",20);
        assertTrue(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_participant_loses_points() {
        porra1.placeBet(participant1,"yes", 20);
        assertEquals(180,participant1.getBettingPoints());
    }

    @org.junit.jupiter.api.Test
    public void placeBet_increases_number_of_bets() {
        porra1.placeBet(participant1, "yes", 20);
        assertEquals(1,porra1.getBets().size());
    }

    @org.junit.jupiter.api.Test
    public void placeBet_increases_number_of_bets2() {
        porra1.placeBet(participant1, "yes", 20);
        porra1.placeBet(participant2, "no", 10);
        assertEquals(2,porra1.getBets().size());
    }

    @org.junit.jupiter.api.Test
    public void placeBet_increases_number_of_bets_of_participant() {
        porra1.placeBet(participant1, "yes", 20);
        assertEquals(1,participant1.getOnHold().size());
    }

    @org.junit.jupiter.api.Test
    public void placeBet_works_extending_options() {
        boolean placed = porra2.placeBet(participant2, "do",100);
        assertTrue(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_works_and_Expand_options() {
        porra2.placeBet(participant2, "do", 100);
        assertEquals(3,porra2.getOptions().size());
    }

    @org.junit.jupiter.api.Test
    public void placeBet_fails_for_not_enough_points() {
        boolean placed = porra1.placeBet(participant1,"yes",220);
        assertFalse(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_fails_for_not_bein_options() {
        boolean placed = porra1.placeBet(participant1, "yellow",20);
        assertFalse(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_fails_for_not_being_active() {
        porra1.setState(Porra.State.WAITING_RESULT);
        boolean placed = porra1.placeBet(participant1, "yes", 20);
        assertFalse(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_fails_because_it_is_too_late() {
        boolean placed = passedPorra.placeBet(participant1,"yes",20);
        assertFalse(placed);
    }

    @org.junit.jupiter.api.Test
    public void placeBet_change_toWaiting_if_it_is_too_late() {
        passedPorra.placeBet(participant1, "yes", 20);
        assertEquals(Porra.State.WAITING_RESULT, passedPorra.getState());
    }

    ///////////////////////////////////////
    // Tests for the resolve method
    //////////////////////////////////////

    @org.junit.jupiter.api.Test
    public void failToResolveForAlreadyResolved() {
        passedPorra.setState(Porra.State.RESOLVED);
        boolean resolved = passedPorra.resolve("yes");
        assertFalse(resolved,"Resolved twice");
    }

    @org.junit.jupiter.api.Test
    public void failToResolveBecuaseTooSoon() {
        boolean resolved = porra1.resolve("yes");
        assertFalse(resolved,"Resolved before the date");
    }

    @org.junit.jupiter.api.Test
    public void resolveNicelly() {
        boolean resolved = passedPorra.resolve("yes");
        assertTrue(resolved, "Not resolved a nice porra");
    }

    @org.junit.jupiter.api.Test
    public void resolveNicellyChangeStatus() {
        passedPorra.resolve("yes");
        assertEquals(Porra.State.RESOLVED, passedPorra.getState());
    }

    @org.junit.jupiter.api.Test
    public void  failToResolveBecauseNotOption () {
        boolean resolved = passedPorra.resolve("cold");
        assertFalse(resolved, "Resolved but it was not an option");
    }

}