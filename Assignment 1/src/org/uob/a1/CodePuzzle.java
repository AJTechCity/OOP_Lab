package org.uob.a1;

import java.util.Scanner;

class CodePuzzle extends Puzzle{

    //Extension of the puzzle class

    /*
        Used by certain roooms - Provides a
        re-usable puzzle that dynamically creates hints for the
        user based off of pre-set formulae.
    */
    
    final private int MAX_GUESSES=10;

    private Scanner scanner = new Scanner(System.in);

    private String codeStr;
    private int[] code;
    private String[] startHints;
    private String[] hints;

    private int guesses = 0;
    private int currentHintIndex=-1;
    private String userInput="";

    public CodePuzzle(String prizeItem, Player puzzleSolver, String code){
        super(
            "Code Puzzle", //puzzle name
            "Can you crack the code? Guess the correct 6-digit combination to unlock the prize. Use hints wisely to discover the digits and their positions", //puzzle description
            prizeItem, //prize item
            puzzleSolver //Player Object
        ); //Calls the super() method to initialise the base class

        this.code = new int[code.length()];
        for(int i=0; i<code.length(); i++){
            this.code[i] = code.charAt(i) - '0';
        }
        this.codeStr = code;
        this.generateHints();
    }

    private void generateHints(){
        //Generates a default set of hints for the user
        this.startHints = new String[code.length];
        this.hints = new String[code.length];

        for (int i=0; i<code.length; i++){ //These are starting hints provided to the user at the start
            if(code[i]%2==0){
                this.startHints[i] = "The digit at position " + (i+1) + " is even";
            }else{
                startHints[i] = "The digit at position " + (i+1) + " is odd";
            }
        }

        //Below hints are the ones the user can request so that the puzzle is made easier

        hints[0] = "The first digit is " + code[0];
        hints[1] = "Digit 5 + Digit 6 = " + (code[4]+code[5]);
        hints[2] = "The sum of the first and second digits is " + (code[0]+code[1]);
        hints[3] = "The product of the second and fifth digit is " + (code[1] * code[4]);
        hints[4] = "The sum of all the digits is " + (code[0] + code[1] + code[2] + code[3] + code[4] + code[5]);
        hints[5] = "The last digit is " + code[5];
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
        //Override of the startPuzzle() method in the Puzzle class so that the program can see there is a game logic and not the default error message
        
        //Reset the variables in case the user in trying again
        guesses = 0;
        userInput="";


        System.out.println("Welcome to the Combination Code Puzzle!");
        System.out.println("The rules are simple: ");
        System.out.println("1) I will give you 6 starting hints which tell you whether each digit is odd or even");
        System.out.println("2) You may type 'hint' to gain a new hint");
        System.out.println("3) Type 'guess <your_code>' to make a guess");
        System.out.println("4) WARNING: You will have a maximum of " + MAX_GUESSES + " guesses!");
        System.out.println("5) Type 'exit' at any time to exit the puzzle and go back to the map");
        System.out.println("Good Luck!");
        System.out.println("\nPress enter to receive your starting hints... ");
        scanner.nextLine(); //Used for aesthetics so user can advance when they have read everything

        this.outputStartHints(); //Used as a separate function to reduce size of current method and so it can be re-used later

        do{
            //Basic do-while loop for command input

            System.out.print("Code Puzzle > ");
            userInput = scanner.nextLine();
            this.parseUserInput(userInput);

        }while(!userInput.equals("exit") && this.guesses < this.MAX_GUESSES && !this.getIsSolved()); //Only breaks if the puzzle is solved, if player makes too many guesses, or if they ask to exit the puzzle

        if(this.guesses >= this.MAX_GUESSES){ 
            //Once loop is broken, if the reason was due to maximum guesses, then alert the user about the reason
            System.out.println("Maximum attempts used! Exiting the room...");
        }

    }

    private void parseUserInput(String command){

        //Another command-passig method which handles incoming user input and executes the required methods

        String[] commandParts = command.split(" ");
        String action = commandParts[0].toLowerCase(); //Used for formatting issues
        switch(action){
            case "guess":
                if(commandParts.length == 2){ //Check the command has the valid number of parts
                    String guess = commandParts[1];
                    if(guess.length() != codeStr.length()){ //If the length is wrong, don't bother checking as that'll count as an attempt
                        System.out.println("Incorrect length - The code is " + code.length + " digits long");
                    }else{ //Attempt to verify the user's guess
                        boolean correctGuess = this.checkGuess(commandParts[1]);
                        if(correctGuess){
                            System.out.println("Correct Guess!");
                            this.puzzleSolved(); //Call puzzleSolved method to update user scores, etc
                        }else{
                            System.out.println("Incorrect! You have used " + guesses + " out of " + MAX_GUESSES + " guesses!");
                        }
                    }
                }else{
                    System.out.println("To use guess command, type 'guess <your_guess>' where your_guess is a 6 digit number");
                }
                break;
            case "exit":
                this.userInput = "exit"; //cause puzzle to exi the do while loop
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
        //Called when the user types 'hint'
        if(++currentHintIndex >= hints.length){
            System.out.println("No more hints are available!");
        }else{
            System.out.println("Hint " + (currentHintIndex+1) + ": " + this.hints[currentHintIndex]);
        }
    }

    private boolean checkGuess(String input){
        guesses++; //Mark a guess as being used
        boolean correct=true;
        int correctPlaces = 0; //Only to show user how many values they have in the correct place

        if(input.length() != codeStr.length()){
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

        return correct; //Let's program know if user's guess was correct or not
    }
}