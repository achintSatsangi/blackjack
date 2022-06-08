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
    void should_player_not_show_blackjack_if_score_is_21() {
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
    void should_player_draw_more_cards_if_score_is_less_than_17() {
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

        assertThat(player.shouldPlayerDrawMoreCards()).isTrue();
    }

    @Test
    void should_player_not_draw_more_cards_if_score_is_17_or_higher() {
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

        assertThat(player.shouldPlayerDrawMoreCards()).isFalse();

        player.getCards().add(Card.builder()
                .suit(Suit.HEARTS)
                .rank(Rank.TWO)
                .build());

        assertThat(player.shouldPlayerDrawMoreCards()).isFalse();
    }

    @Test
    void should_dealer_draw_more_cards_if_score_is_lower_or_same_as_the_player() {
        Player dealer = Player.builder()
                .name("dealer")
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

        assertThat(dealer.shouldDealerDrawMoreCards(17)).isTrue();
        assertThat(dealer.shouldDealerDrawMoreCards(19)).isTrue();
    }

    @Test
    void should_dealer_not_draw_more_cards_if_dealer_score_higher_than_other_player() {
        Player dealer = Player.builder()
                .name("dealer")
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

        assertThat(dealer.shouldDealerDrawMoreCards(15)).isFalse();
    }

}