package org.uob.a1;

import java.util.Scanner;

class KeyPuzzle extends Puzzle{

    //Extension of the puzzle class

    /*
        Used by certain roooms - Provides a
        re-usable puzzle that checks if a user
        has the valid key item to win the prize of the room
    */

    private Scanner scanner = new Scanner(System.in);
    private String requiredKey;
    private String prizeDescription;
    private String userInput="";

    public KeyPuzzle(String requiredKey, String prizeItem, String prizeDescription, Player puzzleSolver){
        super(
            "Key Puzzle", //puzzle name
            "If you have the correct key required, you will unlock the Chest of prize items. There may be 1, 2, 3 or even 0 prizes that await you!", //puzzle description
            prizeItem, //prize item
            puzzleSolver //Player Object
        ); //Calls the super() method to initialise the base class

        this.requiredKey = requiredKey;
        this.prizeDescription = prizeDescription;
    }

    public void startPuzzle(){ 
        //Override of the startPuzzle() method in the Puzzle class so that the program can see there is a game logic and not the default error message

        System.out.println("Welcome to the Key Puzzle!");
        System.out.printlnt("The rules are extremely simple: ");
        System.out.printlnt("1) I will ask if you have a key for me");
        System.out.printlnt("2) You will then type 'Y' for Yes or 'N' for no");
        System.out.printlnt("3) If you lie to me in any way, I will remove 5 points from your score!");
        System.out.println("4) You cannot 'exit' this puzzle before telling the truth!");
        System.out.print("\nPress enter when you have understood the rules... ");
        scanner.nextLine();//Aesthetics

        System.out.println("Do you have the key I am looking for? It is called '" + this.requiredKey + "'.")

        do{
            System.out.println("Type Y/N: ");
            this.userInput = scanner.nextLine().toUpperCase();

            if(this.userInput != "N" || this.userInput != "Y"){
                System.out.println("Invalid Answer! Please try again");
            }
        }while(this.userInput != "N" || this.userInput != "Y");

        boolean hasKey = this.playerHasKey();

        if((hasKey && this.userInput == "Y") || (!hasKey && this.userInput == "N")){ //Truth

            this.getPuzzleSolver().getInventory().removeItem(this.requiredKey);

            System.out.println("Congratulations! You have told the truth");
            System.out.println("I have taken your key, and in exchange have given you a prize of '" + this.prizeItem +"'");
            System.out.println("\nPrize Description: " + this.prizeDescription);
            System.out.print("\nPress enter when you are ready to leave the room... ");
            scanner.nextLine(); //Aesthetics
            this.puzzleSolved(); //Handles adding prize item and exiting the room
        }else{
            this.getPuzzleSolver().getScore().reduceScore(5);
            System.out.println("You LIED to me!!");
            System.out.println("I have taken 5 points and you are leaving my room!");
        }

        
    }

    private boolean playerHasKey(){
        int keyIndex = this.getPuzzleSolver().getInventory().hasItem(this.requiredKey);
        return keyIndex > -1 ? true : false;
    }
}