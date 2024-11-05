package org.uob.a1;

import java.util.Scanner;

class CodePuzzle extends Puzzle{
    
    final private int MAX_GUESSES=10;

    private Scanner scanner = new Scanner(System.in);

    private String codeStr;
    private int[] code;
    private String[] startHints;
    private String[] hints;

    private int guesses = 0;
    private int currentHintIndex=0;
    private String userInput="";

    public CodePuzzle(String prizeItem, Player puzzleSolver, String code){
        super(
            "Code Puzzle", //puzzle name
            "", //puzzle description
            "", //prize item
            puzzleSolver //Player Object
        );

        this.code = new int[code.length()];
        for(int i=0; i<code.length(); i++){
        //     // this.code[i] = (int) code.charAt(i);
        }
        this.codeStr = code;
        this.generateHints();
    }

    private void generateHints(){
        this.startHints = new String[code.length];
        this.hints = new String[code.length];

        for (int i=0; i<code.length; i++){
            if(i%2==0){
                this.startHints[i] = "The digit at position " + (i+1) + " is even";
            }else{
                startHints[i] = "The digit at position " + (i+1) + " is odd";
            }
        }

        hints[0] = "The first number is " + code[0];
        hints[1] = "The number " + code[4] + " is in an odd position";
    }

    public String[] getStartHints(){
        return this.startHints;
    }

    public void outputStartHints(){
        new EasyGameOutputs().printTitle("   STARTING HINTS   ");
        for(int i=0; i<startHints.length;i++){
            System.out.println((i+1) + ") " + startHints[i]);
        }
        System.out.println("\nType 'hint' for another hint");
    }

    public void startPuzzle(){
        System.out.println("Welcome to the Combination Code Puzzle!");
        System.out.println("The rules are simple: ");
        System.out.println("1) I will give you 6 starting hints which tell you whether each digit is odd or even");
        System.out.println("2) You may type 'hint' to gain a new hint");
        System.out.println("3) Type 'guess <your_code>' to make a guess");
        System.out.println("4) WARNING: You will have a maximum of " + MAX_GUESSES + " guesses!");
        System.out.println("5) Type 'exit' at any time to exit the puzzle and go back to the map");
        System.out.println("Good Luck!");
        System.out.println("\nPress enter to receive your starting hints... ");
        scanner.nextLine();

        this.outputStartHints();

        do{

            System.out.print("Code Puzzle > ");
            userInput = scanner.nextLine();
            this.parseUserInput(userInput);

        }while(!userInput.equals("exit"));
    }

    private void parseUserInput(String command){
        String[] commandParts = command.split(" ");
        String action = commandParts[0].toLowerCase();
        switch(action){
            case "guess":
                break;
            case "exit":
                break;
            case "hint":
                this.giveHint();
                break;
            default:
                System.out.println("Invalid Command");
                userInput="";
                break;
        }
    }
    
    private void giveHint(){
        if(++currentHintIndex >= hints.length){
            System.out.println("No more hints are available!");
        }else{
            System.out.println("Hint " + (currentHintIndex+1) + ": " + this.hints[currentHintIndex]);
        }
    }

    private boolean checkGuess(String input){
        guesses++;
        boolean correct=true;
        int correctPlaces = 0;

        if(input.length() != code.length){
            correct=false; //Incorrect length
        }else{
            for(int i=0; i<input.length();i++){
                if(input.charAt(i) != this.codeStr.charAt(i)){
                    correct = false;
                }else{
                    correctPlaces++;
                }
            }
        }

        System.out.println("There are " + correctPlaces + " digits in the correct place");

        return correct;
    }
}