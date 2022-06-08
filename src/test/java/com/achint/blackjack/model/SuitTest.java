package com.achint.blackjack.model;

import org.junit.jupiter.api.Test;

import static com.achint.blackjack.model.Suit.CLUBS;
import static com.achint.blackjack.model.Suit.DIAMONDS;
import static com.achint.blackjack.model.Suit.HEARTS;
import static com.achint.blackjack.model.Suit.SPADES;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SuitTest {

    @Test
    void should_map_valid_suit_values() {
        assertThat(Suit.fromValue('C')).isEqualTo(CLUBS);
        assertThat(Suit.fromValue('D')).isEqualTo(DIAMONDS);
        assertThat(Suit.fromValue('H')).isEqualTo(HEARTS);
        assertThat(Suit.fromValue('S')).isEqualTo(SPADES);
    }

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_input() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> Suit.fromValue('I'));
        assertThat(result.getMessage()).isEqualTo("Invalid suit value [I]");
    }
}