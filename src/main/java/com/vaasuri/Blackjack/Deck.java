package com.vaasuri.Blackjack;

import java.util.ArrayList;
import java.util.Random;

public class Deck
{
private ArrayList<Card> deck;
    
    public Deck() {
        deck = new ArrayList<Card>();
        
        for (Card.Suit suit : Card.Suit.values()) {
            for (int number = 1; number <= 13; number++) {
                deck.add(new Card(suit, number));
            }
        }
    }
    
    public void shuffle() {
        ArrayList<Card> shuffled = new ArrayList<Card>();
        
        Random rand = new Random(System.currentTimeMillis());
        while (!deck.isEmpty()){
            int index = rand.nextInt(deck.size());
            Card removedCard = deck.remove(index);
            shuffled.add(removedCard);
        }
        
        deck = shuffled;
            
    }
    
    public ArrayList<Card> getCards() {
        return (ArrayList<Card>) deck.clone();
    }
    
    public ArrayList<Card> dealCards(int numCards) {
        if (numCards < 1 || numCards > deck.size())
            throw new IllegalArgumentException("invalid number of cards requested");
        ArrayList<Card> cards = new ArrayList<Card>();
        for (int i = 0; i < numCards; i++) {
            cards.add(deck.remove(0));
        }
        return cards;
    }

    public Card dealSingleCard()
    {
        return deck.remove(0);
    }

}
