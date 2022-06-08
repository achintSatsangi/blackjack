package com.achint.blackjack.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

import static java.lang.String.format;

@Getter
@RequiredArgsConstructor
public enum Rank {

    TWO("2", 2),
    THREE("3", 3),
    FOUR("4", 4),
    FIVE("5", 5),
    SIX("6", 6),
    SEVEN("7", 7),
    EIGHT("8", 8),
    NINE("9", 9),
    TEN("10", 10),
    JACK("J", 10),
    QUEEN("Q", 10),
    KING("K", 10),
    ACE("A", 11);

    private final String printValue;
    private final int value;

    public static Rank fromPrintValue(String printValue) {
        return Arrays.stream(Rank.values())
                .filter(r -> r.getPrintValue().equals(printValue))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(format("Invalid rank print value [%s]", printValue)));
    }

}
