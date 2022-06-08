package com.achint.blackjack;

import com.achint.blackjack.model.Deck;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.achint.blackjack.Play.NEW_LINE;
import static org.assertj.core.api.Assertions.assertThat;

class BlackjackTest {

    @Test
    void should_declare_player_as_winner_if_both_start_with_blackjack() {
        Deck deck = new Deck(List.of("HA", "SA", "SK", "HK"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer()).hasToString("sam: HA, SK");
        assertThat(game.getDealer()).hasToString("dealer: SA, HK");
    }

    @Test
    void should_declare_dealer_as_winner_if_both_start_with_22() {
        Deck deck = new Deck(List.of("HA", "SA", "CA", "DA"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("dealer");
        assertThat(game.getPlayer()).hasToString("sam: HA, CA");
        assertThat(game.getDealer()).hasToString("dealer: SA, DA");
    }

    @Test
    void should_declare_dealer_as_winner_if_player_score_goes_above_21() {
        Deck deck = new Deck(List.of("CA", "D5", "H5", "S5", "S7"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getPlayer().getHandScore()).isEqualTo(23);
        assertThat(game.getWinner().getName()).isEqualTo("dealer");
        assertThat(game.getPlayer()).hasToString("sam: CA, H5, S7");
        assertThat(game.getDealer()).hasToString("dealer: D5, S5");
    }

    @Test
    void should_declare_player_as_winner_if_dealer_score_goes_above_21() {
        Deck deck = new Deck(List.of("CA", "D10", "H9", "S5", "S7"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getDealer().getHandScore()).isEqualTo(22);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer()).hasToString("sam: CA, H9");
        assertThat(game.getDealer()).hasToString("dealer: D10, S5, S7");
    }

    @Test
    void should_declare_player_as_winner_if_player_score_higher() {
        Deck deck = new Deck(List.of("CA", "D5", "H9", "HQ", "S8"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer()).hasToString("sam: CA, H9");
        assertThat(game.getDealer()).hasToString("dealer: D5, HQ, S8");
    }

    @Test
    void should_declare_dealer_as_winner_if_dealer_score_higher() {
        Deck deck = new Deck(List.of("CA", "D5", "H8", "H5", "S10"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("dealer");
        assertThat(game.getPlayer()).hasToString("sam: CA, H8");
        assertThat(game.getDealer()).hasToString("dealer: D5, H5, S10");
    }

    @Test
    void should_declare_player_as_winner_for_complex_case() {
        Deck deck = new Deck(List.of("C5", "D5", "S5", "H5", "C2", "C3", "C6", "S10", "S2"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer()).hasToString("sam: C5, S5, C2, C3, C6");
        assertThat(game.getDealer()).hasToString("dealer: D5, H5, S10, S2");
    }

    @Test
    void should_declare_dealer_as_winner_for_complex_case() {
        Deck deck = new Deck(List.of("C4", "D4", "S5", "H5", "C2", "S2", "C6", "S9", "S2"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("dealer");
        assertThat(game.getPlayer()).hasToString("sam: C4, S5, C2, S2, C6");
        assertThat(game.getDealer()).hasToString("dealer: D4, H5, S9, S2");
    }

    @Test
    void should_return_expected_result() {
        Deck deck = new Deck(List.of("HA", "SA", "SK", "HK"));
        Blackjack game = new Blackjack(deck);
        assertThat(game.results(NEW_LINE)).isEqualTo(
                game.getWinner().getName()
                        .concat(NEW_LINE)
                        .concat(game.getPlayer().toString())
                        .concat(NEW_LINE)
                        .concat(game.getDealer().toString()));
    }
}