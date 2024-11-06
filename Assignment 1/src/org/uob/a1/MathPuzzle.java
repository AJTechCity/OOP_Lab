package org.uob.a1;

import java.util.Scanner;

class MathPuzzle extends Puzzle{

    //Extension of the puzzle class

    /*
        Used by certain roooms - Provides a
        re-usable puzzle that asks the user a
        set of maths questions in which they must get
        all correct to secure the points and prize
    */

    private Scanner scanner = new Scanner(System.in);
    private String prizeDescription;
    private String userInput="";

    public MathPuzzle(String prizeItem, String prizeDescription, Player puzzleSolver){
        super(
            "Math Puzzle", //puzzle name
            "A set of mixed difficulty mathetmatics questions designed to test your knowledge. You will need the 'knowledge' item to complete this puzzle", //puzzle description
            prizeItem, //prize item
            puzzleSolver //Player Object
        ); //Calls the super() method to initialise the base class

        this.prizeDescription = prizeDescription;
    }

    public void startPuzzle(){ 
        //Override of the startPuzzle() method in the Puzzle class so that the program can see there is a game logic and not the default error message

        System.out.println("Welcome to the Maths Puzzle!");
        System.out.println("The rules are extremely simple: ");
        System.out.println("1) I will ask you a set of 7 questions");
        System.out.println("2) You must then give me the answer");
        System.out.println("3) If you get any wrong, you will be removed from the room and have to restart!");
        System.out.println("4) You can type 'exit' at any time to leave the puzzle");
        System.out.print("\nPress enter when you have understood the rules... ");
        scanner.nextLine();//Aesthetics

        //TO-DO: Design logic to ask user questions and get their answer input
    }
}