package com.vaasuri.Blackjack;

public class Card
{
    enum Suit {
        SPADES, CLUBS, HEARTS, DIAMONDS;
    }
    
    private Suit suit;
    private int number;
    
    public Card(Suit suit, int number) {
        if (suit == null)
            throw new IllegalArgumentException("Suit must be specified");
        if (number < 1 || number > 13)
            throw new IllegalArgumentException("Invalid card number");
        this.suit = suit;
        this.number = number;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public int getNumber()
    {
        return number;
    }

    @Override
    public String toString()
    {
        String suitName;
        String cardName;
        
        if (suit == Suit.SPADES)
           suitName = "Spades";
        else if (suit == Suit.CLUBS)
            suitName = "Clubs";
        else if (suit == Suit.HEARTS)
            suitName = "Hearts";
        else
            suitName = "Diamonds";
        
        if (number == 1)
            cardName = "Ace";
        else if (number == 11)
            cardName = "Jack";
        else if (number == 12)
            cardName = "Queen";
        else if (number == 13)
            cardName = "King";
        else
            cardName = Integer.toString(number);
        
        return cardName + " " + suitName;
    }

    @Override
    public int hashCode()
    {
        String cardName = toString();
        return cardName.hashCode();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
            return false;
        if (!(obj instanceof Card))
            return false;
        Card card = (Card) obj;
        return ((this.suit == card.suit) && (this.number == card.number));
    }
}
