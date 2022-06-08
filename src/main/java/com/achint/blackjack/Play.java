package com.achint.blackjack;

import com.achint.blackjack.model.Deck;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Play {

    public static final String NEW_LINE = System.getProperty("line.separator");

    public static void main(String[] args) {
        Play play = new Play();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter file path");
        String filepath = scanner.nextLine();
        Deck deck = play.populateDeck(filepath);
        if(isNull(deck)) {
            System.out.println("Invalid input file provided, exiting...");
        } else {
            System.out.println(deck.getCards());
            Blackjack game = new Blackjack(deck);
            System.out.println(game.results(NEW_LINE));
        }
    }

    Deck populateDeck(String filepath) {
        Deck deck = null;
        if(isNotBlank(filepath)) {
            try {
                deck = new Deck(filepath);
            } catch (IllegalArgumentException ex) {
                System.out.println(format("Issue with file contents [%s], root cause: [%s]", filepath, ex.getMessage()));
            } catch (IOException ex) {
                System.out.println(format("Could not read file on path [%s]", filepath));
            }
            return deck;
        } else {
            return new Deck();
        }
    }


}
