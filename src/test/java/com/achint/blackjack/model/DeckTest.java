package com.achint.blackjack.model;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckTest {

    @Test
    void should_contain_52_cards() {
        Deck deck = new Deck();
        assertThat(deck.getCards()).hasSize(52);
    }

    @Test
    void should_populate_deck_from_valid_file() throws IOException {
        String path = this.getClass().getResource("complete_shuffled_deck.txt").getPath();
        Deck deck = new Deck(path);
        assertThat(deck.getCards()).hasSize(52)
                .startsWith(Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.FIVE)
                                .build(),
                            Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.ACE)
                                .build(),
                            Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.EIGHT)
                                .build(),
                            Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.TEN)
                                .build(),
                            Card.builder()
                                .suit(Suit.CLUBS)
                                .rank(Rank.FIVE)
                                .build())
                .containsAll(new Deck().getCards());
    }

    @Test
    void should_thrown_IllegalArgumentException_for_deck_with_duplicate_cards() {
        String path = this.getClass().getResource("deck_with_duplicate_cards.txt").getPath();
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Deck(path));
        assertThat(result.getMessage()).contains("deck_with_duplicate_cards.txt] does not contain 52 unique cards, possible duplicates");
    }

    @Test
    void should_thrown_IllegalArgumentException_for_incomplete_deck() {
        String path = this.getClass().getResource("incomplete_deck_with_valid_cards.txt").getPath();
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Deck(path));
        assertThat(result.getMessage()).contains("incomplete_deck_with_valid_cards.txt] does not contain 52 cards");
    }

    @Test
    void should_thrown_IllegalArgumentException_for_blank_card_values() {
        String path = this.getClass().getResource("incomplete_deck_with_blank_cards.txt").getPath();
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Deck(path));
        assertThat(result.getMessage()).contains("Invalid input []");
    }

    @Test
    void should_thrown_IllegalArgumentException_for_invalid_card_values() {
        String path = this.getClass().getResource("complete_deck_with_invalid_cards.txt").getPath();
        IllegalArgumentException result = assertThrows(IllegalArgumentException.class, () -> new Deck(path));
        assertThat(result.getMessage()).contains("Invalid input [Y789]");
    }


}