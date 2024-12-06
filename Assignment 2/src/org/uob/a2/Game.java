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

    public Game(){}

    public static void main(String[] args){
        setup();
    }

    public static void setup(){
        //Inits the game state, scanner, parser, and tokeniser. Loads the game data from a file and prepares the initial state
        System.out.println("Setting up game");

        GameStateFileParser gameStateFileParser = new GameStateFileParser();
        gameState = gameStateFileParser.parse("data/game.txt");
    }

    public static void start(){
        //Starts the game loop. Continuously reads input, tokenises it, and processses commands until the user decides to quit
    }

    public static void turn(Command command){
        //Processes a single turn based on the provided commmand
    }

}
