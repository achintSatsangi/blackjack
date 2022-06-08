package com.achint.blackjack;

import com.achint.blackjack.model.Card;
import com.achint.blackjack.model.Deck;
import com.achint.blackjack.model.Rank;
import com.achint.blackjack.model.Suit;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.groups.Tuple.tuple;

class BlackjackIntegrationTest {

    @Test
    void should_declare_player_as_winner_if_both_start_with_blackjack() throws IOException {
        Deck deck = new Deck(this.getClass().getResource("deck_beginning_with_two_blackjacks.txt").getPath());
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer().getCards()).containsExactly(Card.builder()
                        .suit(Suit.HEARTS)
                        .rank(Rank.ACE)
                        .build(),
                Card.builder()
                        .suit(Suit.SPADES)
                        .rank(Rank.KING)
                        .build());
        assertThat(game.getDealer().getCards()).containsExactly(Card.builder()
                        .suit(Suit.SPADES)
                        .rank(Rank.ACE)
                        .build(),
                Card.builder()
                        .suit(Suit.HEARTS)
                        .rank(Rank.KING)
                        .build());
    }

    @Test
    void should_declare_dealer_as_winner_if_both_start_with_22() throws IOException {
        Deck deck = new Deck(this.getClass().getResource("deck_beginning_with_two_22.txt").getPath());
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("dealer");
        assertThat(game.getPlayer().getCards()).containsExactly(Card.builder()
                        .suit(Suit.HEARTS)
                        .rank(Rank.ACE)
                        .build(),
                Card.builder()
                        .suit(Suit.CLUBS)
                        .rank(Rank.ACE)
                        .build());
        assertThat(game.getDealer().getCards()).containsExactly(Card.builder()
                        .suit(Suit.SPADES)
                        .rank(Rank.ACE)
                        .build(),
                Card.builder()
                        .suit(Suit.DIAMONDS)
                        .rank(Rank.ACE)
                        .build());
    }

    @Test
    void should_declare_player_as_winner_for_the_problem_from_challenge() throws IOException {
        Deck deck = new Deck(this.getClass().getResource("deck_from_the_challenge.txt").getPath());
        Blackjack game = new Blackjack(deck);
        assertThat(game.getWinner().getName()).isEqualTo("sam");
        assertThat(game.getPlayer().getCards()).extracting("suit", "rank")
                .containsExactly(tuple(Suit.CLUBS, Rank.ACE), tuple(Suit.HEARTS, Rank.NINE));
        assertThat(game.getDealer().getCards()).extracting("suit", "rank")
                .containsExactly(tuple(Suit.DIAMONDS, Rank.FIVE), tuple(Suit.HEARTS, Rank.QUEEN), tuple(Suit.SPADES, Rank.EIGHT));
    }

}