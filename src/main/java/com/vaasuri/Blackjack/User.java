package com.vaasuri.Blackjack;

import java.util.ArrayList;

public class User
{
    private int balance;
    private ArrayList<Card> hand;
    
    public User() {
        balance = 100;
        hand = new ArrayList<Card>();
    }
    
    public void receiveHand(ArrayList<Card> hand) {
        this.hand.addAll(hand);
    }

    public int getBalance()
    {
        return balance;
    }

    // changing from returning cloned hand to actual hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }
    
    public void replaceCardOnHand(int index, Card newCard) {
        if (index < 0 || index >= hand.size())
            throw new IllegalArgumentException("bad index specified");
        hand.set(index, newCard);
    }

    public void setBalance(int balance)
    {
        this.balance = balance;
    }
}
