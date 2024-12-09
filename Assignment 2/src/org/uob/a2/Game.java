package org.uob.a2;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

import org.uob.a2.commands.*;
import org.uob.a2.gameobjects.*;
import org.uob.a2.parser.*;
import org.uob.a2.utils.*;

/**
 * Main class for the game application. Handles game setup, input parsing, and game execution.
 * 
 * <p>
 * This class initializes the game state, reads user input, processes commands, and maintains the game loop.
 * </p>
 */
public class Game {

    private static GameState gameState;
    private static Scanner scanner;
    private static Tokeniser tokeniser;
    private static Parser parser;
    private static boolean gameEnded;

    public static void main(String[] args){


        System.out.println("Running game setup...");
        setup();

        System.out.println("Starting game...");
        //Maybe implement a function where the user can enter their username to load previous data
        start();
    }

    public static void setup(){
        //Inits the game state, scanner, parser, and tokeniser. Loads the game data from a file and prepares the initial state

        GameStateFileParser gameStateFileParser = new GameStateFileParser();
        scanner = new Scanner(System.in);
        tokeniser = new Tokeniser();
        parser = new Parser();
        gameEnded = false;
        String fileName = "defaultGame.txt";

        System.out.println("Setting up game");

        String playedBefore = "";
        String username = "";
        int pw = 0;

        while(playedBefore.equals("")){
            //Ask for their previous username and password
            System.out.print("Have you played before and remember your unique username and password (Y/N): ");
            playedBefore = scanner.nextLine().toLowerCase().trim();


            if(playedBefore.equals("y")){ //Ask about previous data and load it
                System.out.print("Enter your old username: ");
                username = scanner.nextLine().trim();
                while(pw == 0){
                    System.out.print("Enter your 6 digit password: ");
                    pw = scanner.nextInt();
                }
                if(pw / 1000000 > 0 && pw / 1000000 <= 0.0000009){
                    System.out.println("Password must be 6 digits. Please try again");
                    pw=0;
                }

                fileName = username + "_" + Integer.toString(pw) + ".txt";
                System.out.println(fileName);
            }else if(playedBefore.equals("n")){ //Run the normal start setup
                System.out.println("Ok. Initialising new game instance...");
                System.out.print("Please enter a username: ");
                username = scanner.nextLine().trim();
            }else{
                System.out.println("Invalid input. Please try again");
                playedBefore = "";
                //622 658
                //arundp
            }

        }

        try{
            gameState = gameStateFileParser.parse("data/" + fileName);
            gameState.getPlayer().setName(username);
            if(!fileName.equals("defaultGame.txt")){
                gameState.savedFilename = fileName;
            }
        }catch(Exception e){
            System.out.println(e);
            System.out.println("Could not find your information. Running setup again...");
            setup();
        }

    }

    public static void start(){
        //Starts the game loop. Continuously reads input, tokenises it, and processses commands until the user decides to quit
        System.out.println("----- Welcome to The Game -----\n\n");

        System.out.println("Welcome to the game " + gameState.getPlayer().getName() + "!\nType 'help' at any time to show a list of available commands.\nBelow are details about the current room you are in: \n");

        //Give first room info
        Look initialLookCommand = new Look("room");
        turn(initialLookCommand);
        if(gameState.savedFilename != null){
            scanner.nextLine(); //Avoid the scanner reading the text that is about to be outputted
        }

        while(!gameEnded){
            System.out.print("> ");
            String userInput = scanner.nextLine();
            tokeniser.tokenise(userInput);
            try {
                Command parsedCommand = parser.parse(tokeniser.getTokens());
                turn(parsedCommand);
            }catch (CommandErrorException e){
                System.out.println(e);
            }
        }
    }

    public static void turn(Command command){
        //Processes a single turn based on the provided commmand
        //Run the execute() method of the command
        if(command.commandType == CommandType.QUIT){
            System.out.println("Exiting the game...");
            endGame();
        }else{
            String output = command.execute(gameState);
            System.out.println(output);
        }
    }

    public static void endGame(){
        //Save user data to their own file maybe?
        gameEnded = true;
        GameStateFileSaver gameStateFileSaver = new GameStateFileSaver();
        gameStateFileSaver.saveGameState(gameState);
    }

}
