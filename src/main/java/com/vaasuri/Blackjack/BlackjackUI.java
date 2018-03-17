package com.vaasuri.Blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackjackUI
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        User user = new User();
        Dealer dealer = new Dealer(user, deck);
        
        //Print statement to initialize game
        System.out.println("Welcome to Blackjack!");
        
        while (true) {
            displayBalance(user.getBalance());
            if (!getContinueInput(scanner, "Do you want to play? (Y/N)")) {
                System.out.println("Goodbye!");
                break;
            }
            
            dealer.deal();
            
            ArrayList<Card> userHand = user.getHand();
            ArrayList<Card> dealerHand = dealer.getHand();
            displayHand("User", user.getHand());
            System.out.println("Dealer showing: " + dealerHand.get(0));
            
            while (getContinueInput(scanner, "Would you like to hit? (Y/N)")) {
                dealer.hit(userHand);
                displayHand("User", userHand);
                System.out.println("Dealer: " + dealerHand.get(0));
                if (Result.handScore(userHand) >= 21)
                    break;
            }
            
            dealer.dealerHit(dealerHand);
            displayHand("User", userHand);
            displayHand("Dealer", dealerHand);
            Result result = dealer.computeResult();
            dealer.updateUserBalance(result);
            displayResult(result);
            
            // when changing getHand method from clone to returning actual hand, clear() worked
            // otherwise returning cloned hand kept previous card even after clear()
            userHand.clear();
            dealerHand.clear();
        }
        scanner.close();
    }
    
    //changed parameter to ArrayList<Card> hand from Card[] hand
    private static void displayHand(String whichHand, ArrayList<Card> hand)
    {
        
        int i = 1;
        System.out.println(whichHand);
        for (Card card : hand) {
            System.out.println(Integer.toString(i++) + ": " + card);
        }
        System.out.println(whichHand + "score is: " + Result.handScore(hand));
    }

    private static void displayBalance(int balance)
    {
        System.out.println("Your balance is: $" + balance + ".00");
    }

    private static boolean getContinueInput(Scanner scanner, String msg) {
        System.out.println(msg);
        String line = scanner.nextLine();
        return (line.startsWith("y") || line.startsWith("Y"));
    }
    
    private static void displayResult(Result result) {
        System.out.println("Result: " + result.getWinType().name());
        System.out.println("Winnings: $" + result.getWinnings() + ".00");
    }
    
}


