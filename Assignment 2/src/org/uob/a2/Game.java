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
        System.out.println("Setting up game");

        GameStateFileParser gameStateFileParser = new GameStateFileParser();
        gameState = gameStateFileParser.parse("data/game2.txt");
        scanner = new Scanner(System.in);
        tokeniser = new Tokeniser();
        parser = new Parser();
        gameEnded = false;
    }

    public static void start(){
        //Starts the game loop. Continuously reads input, tokenises it, and processses commands until the user decides to quit
        System.out.println("----- Welcome to The Game -----");

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
    }

}
