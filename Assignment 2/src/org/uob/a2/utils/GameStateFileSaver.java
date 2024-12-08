package org.uob.a2.utils;

import java.io.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

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

    public GameStateFileSaver(){}

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

        System.out.println(savedData.toString());

        try{
            saveToFile(player.getName() + "_game.txt", savedData.toString());
            System.out.println("Successfully Saved Game data to file");
        }catch(IOException e){
            System.out.println("Error saving game data...");
            System.out.println(e);
        }

    }

    private static void saveToFile(String fileName, String content) throws IOException {
        Path path = Path.of("data", fileName);

        Files.writeString(
                path,
                content,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
        );
    }
}
