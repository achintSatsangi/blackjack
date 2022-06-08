package com.achint.blackjack.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class Player {

    private String name;

    @Builder.Default
    private List<Card> cards = new ArrayList<>();

    @ToString.Include
    public int getHandScore() {
        return cards.stream()
                .mapToInt(c -> c.getRank().getValue())
                .sum();
    }

    public boolean hasBlackJack() {
        return this.getHandScore() == 21;
    }

    public boolean shouldDrawMoreCards() {
        return this.getHandScore() < 17;
    }

}

