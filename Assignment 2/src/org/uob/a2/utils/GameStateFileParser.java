package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;

import org.uob.a2.gameobjects.*;

/**
 * Utility class for parsing a game state from a file.
 * 
 * <p>
 * This class reads a structured text file to create a {@code GameState} object,
 * including the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileParser {

    public GameStateFileParser(){}

    public static GameState parse(String filename){
//        Player player = null;
//        Room currentRoom = null;
//
//        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
//            String line;
//
//            while((line = reader.readLine()) != null){
//                line = line.trim();
//
//                if(line.startsWith("player:")){
//                    String playerName = line.split(":")[1].trim();
//                    player = new Player(playerName);
//                }
//            }
//        }

        return null;
    }
   
}
