package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import org.uob.a2.gameobjects.*;
import org.uob.a2.commands.*;

/**
 * Utility class for saving a game state to a file.
 *
 * <p>
 * This class formats a {@code GameState} GameState object as a structured text file,
 * includes the player, map, rooms, items, equipment, features, and exits.
 * </p>
 */
public class GameStateFileSaver {

    private static Random random;

    public GameStateFileSaver(){
        random = new Random();
    }

    public static void saveGameState(GameState gameState){
        StringBuilder savedData = new StringBuilder("");
        Player player = gameState.getPlayer();

        System.out.println("Dropping all of player's inventory in current room...");
        for(GameObject obj : player.getAll()){
            //Drop all player objects in current room
            Drop dropCommand = new Drop(obj.getName());
            System.out.println(dropCommand.execute(gameState));
        }

        savedData.append("player:" + player.toYAML()); //Save player name data
        savedData.append("\n");

        //Save Map Room ID
        savedData.append("map:" + gameState.getMap().toYAML());
        savedData.append("\n");

        ArrayList<Room> rooms = gameState.getMap().getRooms();
        for(Room room : rooms){
            //Save each room data and its equipments, containers, items, and exits (GameObjects)
            savedData.append("\troom:" + room.toYAML());
            savedData.append("\n");
            for(GameObject obj: room.getAll()){
                savedData.append("\t\t" + obj.gameObjectYAMLType + ":" + obj.toYAML());
                savedData.append("\n");
            }
        }

        //Save any unused combination data
        for(Combination combination : gameState.getCombinations()){
            if(combination.isCombinationUsed() == false){ //Only save combinations which haven't been used
                savedData.append("combination:" + combination.toYAML());
                savedData.append("\n");
            }
        }

        //Finally, we can save the score
        savedData.append("score:" + player.getScore().getScore());

//        System.out.println(savedData.toString());

        try{
            String pw;
            if(gameState.savedFilename != null){
                String[] fileNameData = gameState.savedFilename.split("_");

                saveToFileFunc(Path.of("data", gameState.savedFilename), savedData.toString());
                pw = fileNameData[1].replace(".txt", "");
            }else{
                pw = saveToFile(player.getName(), savedData.toString());
            }
            System.out.println("Successfully Saved Game data to file");
            System.out.println("Your unique password is: " + pw + "\nYour Username is: " + player.getName() + "\nDO NOT LOSE THIS INFO AS YOUR SAVED DATA WILL BE LOST");
        }catch(IOException e){
            System.out.println("Error saving game data...");
            System.out.println(e);
        }

    }

    private static String saveToFile(String playerName, String content) throws IOException {
        //Returns the random 6 digit password / number
        String pw =  Integer.toString(100000 + random.nextInt(900000));
        Path path = Path.of("data", playerName + "_" + pw + ".txt");

        saveToFileFunc(path, content);

        return pw;
    }

    private static void saveToFileFunc(Path path, String content) throws IOException {
        Files.writeString(
                path,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}
