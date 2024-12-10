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
    }

    public static void setup(){
        //Inits the game state, scanner, parser, and tokeniser. Loads the game data from a file and prepares the initial state

        GameStateFileParser gameStateFileParser = new GameStateFileParser();
        scanner = new Scanner(System.in);
        tokeniser = new Tokeniser();
        parser = new Parser();
        gameEnded = false;
        String fileName = "newDefaultGame.txt";

        System.out.println("Setting up game");

        int menuChoice = 0;
        String username = "";
        int pw = 0;

        while(menuChoice < 1 || menuChoice > 3){
            //Ask for their previous username and password
            System.out.println("Welcome to the Game\nPlease choose a menu option below");
            System.out.println("1) New Game");
            System.out.println("2) Load Game");
            System.out.println("3) Quit");
            System.out.print("Please enter an option (1-3): ");
            try {
                menuChoice = scanner.nextInt();
            } catch (java.lang.Exception e) {
                System.out.println("Invalid menu choice");
            }finally{
                scanner.nextLine();
            }

            if(menuChoice == 3){
                System.out.println("Quitting game");
                return;
            }

            if(menuChoice == 2){ //Ask about previous data and load it
                System.out.print("Enter your old username: ");
                username = scanner.nextLine().trim();
                while(pw == 0) {
                    System.out.print("Enter your 6 digit password: ");
                    try {
                        pw = scanner.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid Password. It must be a 6 digit number");
                    } finally {
                        scanner.nextLine();
                    }

                    if (Integer.toString(pw).length() != 6) {
                        System.out.println("Password must be 6 digits. Please try again");
                        pw = 0;
                    }
                }

                fileName = username + "_" + Integer.toString(pw) + ".txt";
            }else if(menuChoice == 1){ //Run the normal start setup
                System.out.println("Initialising new game instance...");
                System.out.print("Please enter a username: ");
                username = scanner.nextLine().trim();
            }else{
                System.out.println("Invalid Menu Choice");
            }

        }

        try{
            gameState = gameStateFileParser.parse("data/" + fileName);
            gameState.getPlayer().setName(username);
            if(!fileName.equals("newDefaultGame.txt")){
                gameState.savedFilename = fileName;
            }

            System.out.println("Starting game...");
            try {
                start();
            }catch(Exception e){
                System.out.println(e);
            }
        }catch(Exception e){
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
