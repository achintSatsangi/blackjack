package com.achint.blackjack.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@Builder
@Getter
@EqualsAndHashCode
@RequiredArgsConstructor
public class Card {

    private final Suit suit;
    private final Rank rank;

    @Override
    public String toString() {
        return suit.getValue() + rank.getPrintValue();
    }

    public Card(@NonNull String printValue) {
        int length = printValue.length();
        if(length < 2 || length > 3) {
            throw new IllegalArgumentException(format("Invalid input [%s]", printValue));
        }
        this.suit = Suit.fromValue(printValue.charAt(0));
        this.rank = Rank.fromPrintValue(printValue.substring(1, length));
    }
}
