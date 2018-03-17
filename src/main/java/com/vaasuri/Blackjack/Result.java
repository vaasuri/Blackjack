package com.vaasuri.Blackjack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public class Result
{
public static int[] WINNING_ODDS = {0, 0, 2, 0, 0, 1, 1};
    
    enum WinType {
        NOWIN, TIE, USERBLACKJACK, DEALERBLACKJACK, USERBUST, DEALERBUST, REGULARWIN;
    }
    
    private WinType winType;
    private ArrayList<Card> dealerHand;
    private ArrayList<Card> userHand;
    private int winnings;
    
    public Result(ArrayList<Card> userHand, ArrayList<Card> dealerHand) {
        if (userHand.size() < 2 || dealerHand.size() < 2)
            throw new IllegalArgumentException("Invalid number of cards in hand");
        this.userHand = userHand;
        this.dealerHand = dealerHand;
        if (isUserBlackjack())
            winType = WinType.USERBLACKJACK;
        else if (isDealerBlackjack())
            winType = WinType.DEALERBLACKJACK;
        else if (isUserBust())
            winType = WinType.USERBUST;
        else if (isDealerBust())
            winType = WinType.DEALERBUST;
        else if (isRegularWin())
            winType = WinType.REGULARWIN;
        else if (isTie())
            winType = WinType.TIE;
        else
            winType = WinType.NOWIN;
        
        winnings = WINNING_ODDS[winType.ordinal()];
    }
    
    

    public static class cardComparator implements Comparator<Card> {

        @Override
        public int compare(Card card1, Card card2)
        {
            if (card1.getNumber() == card2.getNumber())
                return card1.getSuit().ordinal() - card2.getSuit().ordinal();
            else
                return card1.getNumber() - card2.getNumber();
        }
        
    }
    
    public static int handScore(ArrayList<Card> hand) {
        
        int handScore = 0;
        for (Card card : hand) {
            if (card.getNumber() == 11 || card.getNumber() == 12 || card.getNumber() == 13)
                handScore += 10;
            else if (card.getNumber() == 1 && (handScore + 11) <= 21) // had card.getNumber() before changing to handScore
                handScore += 11;
            else
                handScore += card.getNumber();
        }
        return handScore;
    }
    
    public boolean isTie()
    {
        return ((handScore(userHand) == handScore(dealerHand)) || (isUserBlackjack() && isDealerBlackjack()));
    }
    
    public boolean isBlackjack(ArrayList<Card> hand) {
        
        if (hand.size() != 2)
            return false;
        
        Card card1 = hand.get(0);
        Card card2 = hand.get(1);
        
        if (card1.getNumber() == 1) {
            return card2.getNumber() >= 10;
        }
        else if (card1.getNumber() >= 10) {
            return card2.getNumber() == 1;
        }
        else
            return false;        
    }
    
    public boolean isDealerBlackjack() {
        return isBlackjack(dealerHand);
    }
    
    public boolean isUserBlackjack() {
        return isBlackjack(userHand);
    }
    
    public boolean isBust(ArrayList<Card> hand) {
        if (hand.size() != 2) {
            return false;
        }
        
        return handScore(hand) > 21;
    }
    
    public boolean isUserBust() {
        if (!isBust(dealerHand))
            return isBust(userHand);
        return false;
    }
    
    public boolean isDealerBust() {
        if (!isBust(userHand))
            return isBust(dealerHand);
        return false;
    }
    
    public boolean isRegularWin() {
        
        return handScore(userHand) > handScore(dealerHand);
    }
    
    

    public WinType getWinType()
    {
        return winType;
    }

    public ArrayList<Card> getUserHand()
    {
        return userHand;
    }
    
    public ArrayList<Card> getDealerHand() {
        return dealerHand;
    }

    public int getWinnings()
    {
        return winnings;
    }
}
