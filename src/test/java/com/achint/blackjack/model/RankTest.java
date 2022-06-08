package com.achint.blackjack.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RankTest {

    @Test
    void should_map_valid_rank_values() {
        assertThat(Rank.fromPrintValue("2")).isEqualTo(Rank.TWO);
        assertThat(Rank.fromPrintValue("3")).isEqualTo(Rank.THREE);
        assertThat(Rank.fromPrintValue("4")).isEqualTo(Rank.FOUR);
        assertThat(Rank.fromPrintValue("5")).isEqualTo(Rank.FIVE);
        assertThat(Rank.fromPrintValue("6")).isEqualTo(Rank.SIX);
        assertThat(Rank.fromPrintValue("7")).isEqualTo(Rank.SEVEN);
        assertThat(Rank.fromPrintValue("8")).isEqualTo(Rank.EIGHT);
        assertThat(Rank.fromPrintValue("9")).isEqualTo(Rank.NINE);
        assertThat(Rank.fromPrintValue("10")).isEqualTo(Rank.TEN);
        assertThat(Rank.fromPrintValue("J")).isEqualTo(Rank.JACK);
        assertThat(Rank.fromPrintValue("Q")).isEqualTo(Rank.QUEEN);
        assertThat(Rank.fromPrintValue("K")).isEqualTo(Rank.KING);
        assertThat(Rank.fromPrintValue("A")).isEqualTo(Rank.ACE);
    }

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_input() {
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> Rank.fromPrintValue("11"));
        assertThat(result.getMessage()).isEqualTo("Invalid rank print value [11]");
    }
}