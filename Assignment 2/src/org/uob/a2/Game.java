package org.uob.a2;

import java.util.Scanner;

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
    private Scanner scanner;

    public Game(){
        this.gameState = new GameState();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args){
        System.out.println("Running game setup...");
        setup();

        System.out.println("Starting game...");

        start();
    }

    public static void setup(){
        //Inits the game state, scanner, parser, and tokeniser. Loads the game data from a file and prepares the initial state
        System.out.println("Setting up game");

        GameStateFileParser gameStateFileParser = new GameStateFileParser();
        gameState = gameStateFileParser.parse("data/game.txt");
    }

    public static void start(){
        //Starts the game loop. Continuously reads input, tokenises it, and processses commands until the user decides to quit
        System.out.println("----- Welcome to The Map -----");


    }

    public static void turn(Command command){
        //Processes a single turn based on the provided commmand
        //Run the execute() method of the command
        if(command.commandType == CommandType.QUIT){
            System.out.println("Exiting the game...");
            endGame();
        }else{
            command.execute(gameState);
        }
    }

    public static void endGame(){

    }

}
