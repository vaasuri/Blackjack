package com.vaasuri.Blackjack;

import java.util.ArrayList;

public class Dealer
{
    private User user;
    private Deck deck;
    private ArrayList<Card> hand;
    private ArrayList<Card> userHand;

    public Dealer(User user, Deck deck)
    {
        this.user = user;
        this.deck = deck;
        this.hand = new ArrayList<>();
    }

    public void deal()
    {
        userHand = new ArrayList<>();
        
        deck.shuffle();
        userHand.add(deck.dealSingleCard());
        hand.add(deck.dealSingleCard());
        userHand.add(deck.dealSingleCard());
        hand.add(deck.dealSingleCard());
        user.receiveHand(userHand);
        
    }
    
    public void hit(ArrayList<Card> hand) {
        hand.add(deck.dealSingleCard());
    }
    
    public void dealerHit(ArrayList<Card> hand) {
        while (Result.handScore(hand) < 17) {
            hit(hand);
            if (Result.handScore(hand) >= 17 && Result.handScore(hand) <= 21)
                break;
        }  
    }

    public void reDeal(int[] replaceCards)
    {
        if (replaceCards == null)
            throw new IllegalArgumentException("bad argument");
        if (replaceCards.length == 0)
            return;
        if (replaceCards.length > 5)
            throw new IllegalArgumentException("attempting to replace too many cards");
        for (int index : replaceCards) {
            user.replaceCardOnHand(index, deck.dealSingleCard());
        }
    }

    public Result computeResult()
    {
        return new Result(user.getHand(), getHand());
    }
    
    // changing from returning cloned hand to actual hand
    public ArrayList<Card> getHand()
    {
        return hand;
    }


    public void updateUserBalance(Result result)
    {
        int balance = user.getBalance();
        if (result.getWinType() == Result.WinType.NOWIN || 
                result.getWinType() == Result.WinType.DEALERBLACKJACK || 
                result.getWinType() == Result.WinType.USERBUST)
            balance -= 1;
        else
            balance += result.getWinnings();
        user.setBalance(balance);
        
    }
}
