package com.achint.blackjack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public enum Suit {

    CLUBS('C'),
    DIAMONDS('D'),
    HEARTS('H'),
    SPADES('S');

    private final char value;

    public static Suit fromValue(char value) {
        return Arrays.stream(Suit.values())
                .filter(r -> r.getValue() == value)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Invalid suit value [%s]", value)));
    }
}
