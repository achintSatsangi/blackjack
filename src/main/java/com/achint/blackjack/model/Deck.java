package com.achint.blackjack.model;

import lombok.Getter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@Getter
public class Deck {

    private List<Card> cards;

    public Deck() {
        this.cards = new LinkedList<>();
        stream(Suit.values())
                .forEach(s -> stream(Rank.values())
                        .forEach(r ->
                                cards.add(Card.builder()
                                        .suit(s)
                                        .rank(r)
                                        .build())));
        Collections.shuffle(cards);
    }

    public Deck(String filePath) throws IOException {
        String fileContent = Files.readString(Paths.get(filePath));
        List<Card> cardList = stream(fileContent.split(","))
                .map(s -> new Card(s.trim()))
                .toList();
        if (cardList.size() != 52) {
            throw new IllegalArgumentException(format("File [%s] does not contain 52 cards", filePath));
        } else if (!cardList.containsAll(new Deck().getCards())) {
            throw new IllegalArgumentException(format("File [%s] does not contain 52 unique cards, possible duplicates", filePath));
        }
        this.cards = cardList;
    }

    // Used for testing only, to build incomplete decks for ease of testing
    public Deck(List<String> cardValues) {
        this.cards = cardValues.stream()
                .map(s -> new Card(s.trim()))
                .collect(toList());
    }

}
