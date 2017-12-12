package com.benjamin;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RedistributionTest {

    Participant participant1;
    Participant participant2;
    Participant participant3;
    Porra porra1;

    @org.junit.jupiter.api.BeforeEach
    public void setup() {
        participant1 = new Participant("Ben", 200);
        participant2 = new Participant("Mari", 200);
        participant3 = new Participant("Oscar", 200);
        List<String> options = Arrays.asList(new String[] {"yes","no"});
        porra1 = new Porra("ex", "Is the sun yellow?", false,
                LocalDate.of(2018, Month.JANUARY,15),
                options);
        porra1.placeBet(participant1,"yes",20);
        porra1.placeBet(participant1, "no", 10);
        porra1.placeBet(participant2, "yes", 5);
        porra1.placeBet(participant2,"yes", 5);
        porra1.placeBet(participant3, "no", 50);

        porra1.setLastBettingDate(LocalDate.of(2017,Month.JANUARY,15));
    }

    @org.junit.jupiter.api.Test
    public void check_that_everything_is_alright() {
        assertTrue(porra1.resolve("yes"));
    }

    @org.junit.jupiter.api.Test
    public void resolve_test_score_change_1() {
        porra1.resolve("yes");
        assertEquals(150, participant3.getScore(),0.01);
    }

    @org.junit.jupiter.api.Test
    public void resolve_test_score_change_2() {
        porra1.resolve("yes");
        assertEquals(230, participant1.getScore());
    }

    @org.junit.jupiter.api.Test
    public void resolve_test_score_change_3() {
        porra1.resolve("yes");
        assertEquals(220, participant2.getScore());
    }

    @org.junit.jupiter.api.Test
    public void resolve_test_betting_points_change_1() {
        porra1.resolve("yes");
        assertEquals(150, participant3.getBettingPoints());
    }

}
