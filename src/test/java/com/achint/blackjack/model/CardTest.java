package com.achint.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CardTest {

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_input() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Card("1"));
        assertThat(result.getMessage()).isEqualTo("Invalid input [1]");
    }

    @Test
    void should_map_card_from_string() {
        assertThat(new Card("CA")).extracting("suit", "rank")
                .containsExactly(Suit.CLUBS, Rank.ACE);
        assertThat(new Card("S2")).extracting("suit", "rank")
                .containsExactly(Suit.SPADES, Rank.TWO);
    }

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_suit_input() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Card("I3"));
        assertThat(result.getMessage()).isEqualTo("Invalid suit value [I]");
    }

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_rank_input() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Card("C12"));
        assertThat(result.getMessage()).isEqualTo("Invalid rank print value [12]");
    }
}