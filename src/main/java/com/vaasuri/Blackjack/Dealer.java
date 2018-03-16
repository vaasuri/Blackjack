package com.vaasuri.Blackjack;

import java.util.ArrayList;

public class Dealer
{
    private User user;
    private Deck deck;
    private ArrayList<Card> hand;
    ArrayList<Card> userHand;

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
        return new Result(user.getHand());
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
