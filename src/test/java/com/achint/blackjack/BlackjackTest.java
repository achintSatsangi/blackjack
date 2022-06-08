package com.achint.blackjack;

import org.junit.jupiter.api.Test;

import static com.achint.blackjack.Blackjack.MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BlackjackTest {

    private Blackjack blackjack = new Blackjack();

    @Test
    void should_test() {
        assertEquals(MESSAGE, blackjack.description());
    }

}