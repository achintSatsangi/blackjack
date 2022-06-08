package com.achint.blackjack;

import com.achint.blackjack.model.Deck;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayTest {

    private Play play = new Play();

    @Test
    void should_populate_random_deck_if_null_file_input() {
        Deck result = play.populateDeck(null);
        assertThat(result.getCards()).hasSize(52);
    }

    @Test
    void should_populate_random_deck_if_blank_file_input() {
        Deck result = play.populateDeck("");
        assertThat(result.getCards()).hasSize(52);
    }

    @Test
    void should_not_populate_deck_for_invalid_file_input() {
        Deck result = play.populateDeck("abcd123");
        assertThat(result).isNull();
    }

    @Test
    void should_not_populate_deck_for_file_containing_invalid_card_data() {
        Deck result = play.populateDeck(this.getClass().getResource("deck_with_invalid_cards.txt").getPath());
        assertThat(result).isNull();
    }

    @Test
    void should_populate_deck_for_file_containing_valid_card_data() {
        Deck result = play.populateDeck(this.getClass().getResource("deck_from_the_challenge.txt").getPath());
        assertThat(result.getCards()).hasSize(52)
                .containsAll(new Deck().getCards());
    }

}