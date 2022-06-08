package com.achint.blackjack;

import com.achint.blackjack.model.Deck;
import com.achint.blackjack.model.Player;
import lombok.Getter;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Getter
public class Blackjack {

    private Deck deck;
    private Player player;
    private Player dealer;
    private Player winner;

    public Blackjack(Deck deck) {
        this.deck = deck;
        this.player = Player.builder()
                .name("sam")
                .build();
        this.dealer = Player.builder()
                .name("dealer")
                .build();

        this.initializeTheGame();
        this.performInitialChecks();
        while (this.isNotFinished() && this.player.shouldPlayerDrawMoreCards()) {
            this.playerDrawsTheTopCard();
        }
        while (this.isNotFinished() && this.dealer.shouldDealerDrawMoreCards(this.player.getHandScore())) {
            this.dealerDrawsTheTopCard();
        }
        if(this.isNotFinished()) {
            this.performFinalCheck();
        }
    }

    public String results(String newline) {
        return this.getWinner().getName()
                .concat(newline)
                .concat(this.player.toString())
                .concat(newline)
                .concat(this.dealer.toString());
    }

    private void initializeTheGame() {
        playerDrawsTheTopCard();
        dealerDrawsTheTopCard();
        playerDrawsTheTopCard();
        dealerDrawsTheTopCard();
    }

    private boolean isNotFinished() {
        return isNull(winner);
    }

    private void performInitialChecks() {
        if(player.hasBlackJack() && dealer.hasBlackJack()) {
            winner = player;
        }
        if(player.getHandScore() == 22 && dealer.getHandScore() == 22) {
            winner = dealer;
        }
    }

    private void playerDrawsTheTopCard() {
        giveTopMostCard(player);
        if(player.getHandScore() > 21) {
            this.winner = dealer;
        }
    }

    private void dealerDrawsTheTopCard() {
        giveTopMostCard(dealer);
        if(dealer.getHandScore() > 21) {
            this.winner = player;
        }
    }

    private void giveTopMostCard(Player player) {
        player.getCards().add(deck.getCards().get(0));
        deck.getCards().remove(0);
    }

    private void performFinalCheck() {
        if(player.getHandScore() > dealer.getHandScore()) {
            this.winner = player;
        } else {
            this.winner = dealer;
        }
    }

}