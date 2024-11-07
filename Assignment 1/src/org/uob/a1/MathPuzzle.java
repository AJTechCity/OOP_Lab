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
    private int userInput;
    
    private String[] questions;
    private int[] answers;




    public MathPuzzle(String[] questions, int[] answers, String prizeItem, String prizeDescription, Player puzzleSolver){
        super(
            "Math Puzzle", //puzzle name
            "A set of mixed difficulty mathetmatics questions designed to test your knowledge. You will need the 'knowledge' item to complete this puzzle", //puzzle description
            prizeItem, //prize item
            puzzleSolver //Player Object
        ); //Calls the super() method to initialise the base class

        this.prizeDescription = prizeDescription;
        this.questions = questions;
        this.answers = answers;

        if(this.answers.length != this.questions.length){
            throw new Error("Questions and answers arrays are not the same length");
        }
    }

    public void startPuzzle(){ 
        //Override of the startPuzzle() method in the Puzzle class so that the program can see there is a game logic and not the default error message
        //Reset user variables in case they are playing again
        userInput=0;

        if(this.getPuzzleSolver().getInventory().hasItem("knowledge") > -1){ //Check if the player has the 'knowledge' item required to solve math puzzles
            System.out.println("Welcome to the Maths Puzzle!");
            System.out.println("The rules are extremely simple: ");
            System.out.println("1) I will ask you a set of " + this.questions.length + " questions");
            System.out.println("2) You must then give me the answer");
            System.out.println("3) If you get any wrong, you will be removed from the room and have to restart!");
            System.out.print("\nPress enter when you have understood the rules... ");
            scanner.nextLine();//Aesthetics

            //TO-DO: Design logic to ask user questions and get their answer input

            for(int i=0;i<questions.length;i++){
                System.out.print((i+1) + " - " + questions[i] + ": ");

                userInput = scanner.nextInt();

                if(userInput != answers[i]){
                    System.out.println("Incorrect! - Puzzle over");
                    return;
                }else{
                    System.out.println("Correct, onto the next question!");
                }
            }

            //Can only reach this if all questions are answered correctly
            System.out.println("Congratulations! You got all the questions correct");
            this.puzzleSolved();
        }else{
            System.out.println("You do not have the required knowledge to complete a math puzzle. Continue playing until you find the 'knowledge' item!");
        }
    }
}