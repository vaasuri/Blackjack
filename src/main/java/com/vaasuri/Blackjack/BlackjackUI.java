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
            while (getContinueInput(scanner, "Would you like to hit? (Y/N)")) {
                deck.dealSingleCard();
            }
            
            
            displayHand(user.getHand());
            Result result = dealer.computeResult();
            dealer.updateUserBalance(result);
            displayResult(result);
            
        }
        scanner.close();
    }
    
    //changed parameter to ArrayList<Card> hand from Card[] hand
    private static void displayHand(ArrayList<Card> hand)
    {
        int i = 1;
        for (Card card : hand) {
            System.out.println(Integer.toString(i++) + ": " + card);
        }
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
    
    private static int[] selectCardsToReplace(Scanner scanner, String msg, ArrayList<Card> cards) {
        int[] replacementArray;
        
        while (true) {
            displayHand(cards);
            System.out.print("Enter card numbers you want to replace seaparated by spaces: ");
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                replacementArray = new int[0];
                return replacementArray;
            }
            String[] replacementInput = input.split(" ");
            if (replacementInput.length > 5) {
                System.out.println("Invalid input, try again.");
                continue;
            }
            replacementArray = new int[replacementInput.length];
            boolean success = true;
            for (int i = 0; i < replacementInput.length; i++) {
                int num = Integer.parseInt(replacementInput[i]);
                if (num < 1 || num > 5) {
                    success = false;
                    System.out.println("Invalid input, try again.");
                    break;
                }
                replacementArray[i] = num - 1;
            }
            if (success)
                break;
        }
        
        return replacementArray;
    }
    
    private static void displayResult(Result result) {
        System.out.println("Result: " + result.getWinType().name());
        System.out.println("Winnings: $" + result.getWinnings() + ".00");
    }
    
}


