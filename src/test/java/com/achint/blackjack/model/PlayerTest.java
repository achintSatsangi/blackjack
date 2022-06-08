package com.achint.blackjack.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @Test
    void should_return_hand_score() {
        Player player = Player.builder()
                .name("john")
                .cards(List.of(Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.FIVE)
                                .build(),
                        Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.SIX)
                                .build()
                ))
                .build();

        assertThat(player.getHandScore()).isEqualTo(11);
    }

    @Test
    void should_show_blackjack_if_score_is_21() {
        Player player = Player.builder()
                .name("john")
                .cards(List.of(Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.ACE)
                                .build(),
                        Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.KING)
                                .build()
                ))
                .build();

        assertThat(player.hasBlackJack()).isTrue();
    }

    @Test
    void should_not_show_blackjack_if_score_is_21() {
        Player player = Player.builder()
                .name("john")
                .cards(List.of(Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.KING)
                                .build(),
                        Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.KING)
                                .build()
                ))
                .build();

        assertThat(player.hasBlackJack()).isFalse();
    }

    @Test
    void should_draw_more_cards_if_score_is_less_than_17() {
        Player player = Player.builder()
                .name("john")
                .cards(List.of(Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.FIVE)
                                .build(),
                        Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.FOUR)
                                .build()
                )).build();

        assertThat(player.shouldDrawMoreCards()).isTrue();
    }

    @Test
    void should_not_draw_more_cards_if_score_is_17_or_higher() {
        Player player = Player.builder()
                .name("john")
                .cards(new ArrayList<>(Arrays.asList(Card.builder()
                                .suit(Suit.SPADES)
                                .rank(Rank.KING)
                                .build(),
                        Card.builder()
                                .suit(Suit.HEARTS)
                                .rank(Rank.SEVEN)
                                .build()
                )))
                .build();

        assertThat(player.shouldDrawMoreCards()).isFalse();

        player.getCards().add(Card.builder()
                .suit(Suit.HEARTS)
                .rank(Rank.TWO)
                .build());

        assertThat(player.shouldDrawMoreCards()).isFalse();
    }

}