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
        GameState gameState = null;
        Player player = null;
        Map map = null;
        Room currentRoom = null;

        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;

            while((line = reader.readLine()) != null){
                line = line.trim();

                if(line.startsWith("player:")){
                    String playerName = line.split(":")[1].trim();
                    player = new Player(playerName);
                }else if(line.startsWith("map:")){
                    map = new Map();
                    String currentRoomId = line.split(":")[1].trim();
                    map.setCurrentRoom(currentRoomId);
                }else if(line.startsWith("room:")){
                    //Set the temporary currentRoom variable so that we update equipment, container, etc for the correct room
                    String[] roomInfo = line.split(":")[1].trim().split(",");
                    Room newRoom = new Room(
                            roomInfo[0], //(String) ID
                            roomInfo[1], //(String) name
                            roomInfo[2], //(String) Description
                            roomInfo[3].equals("false") ? false : true //(Boolean) isHidden
                    );
                    map.addRoom(newRoom);
                    currentRoom=newRoom;
                }else if(line.startsWith("equipment")){
                    //Split the equipment info into array values - Index 0 is the id, index 1 is name etc
                    String[] equipmentInfo = line.split(":")[1].trim().split(",");
                    UseInformation equipmentUseInfo = new UseInformation(
                            false, //isUsed
                            equipmentInfo[4], //(String) use Action
                            equipmentInfo[5], //(String) use target
                            equipmentInfo[6], //(String) use Result
                            equipmentInfo[7] //(String) Use Description
                    );

                    Equipment newEquipment = new Equipment(
                            equipmentInfo[0], //(String) ID
                            equipmentInfo[1], //(String) Name
                            equipmentInfo[2], //(String) Description
                            equipmentInfo[3].equals("false") ? false : true, //(Boolean) Hidden
                            equipmentUseInfo
                    );
                    //Add equipment to current working room
                    currentRoom.addEquipment(newEquipment);
                }else if(line.startsWith("container:")){
                    String[] containerInfo = line.split(":")[1].trim().split(",");
                    Container newContainer = new Container(
                            containerInfo[0], //(String) ID
                            containerInfo[1], //(String) Name
                            containerInfo[2], //(String) Description
                            containerInfo[3].equals("false") ? false : true //(Boolean) isHidden
                    );

                    //Add contianer to current room
                    currentRoom.addFeature(newContainer);
                }else if(line.startsWith("item:")){
                    String[] itemInfo = line.split(":")[1].trim().split(",");
                    Item newItem = new Item(
                            itemInfo[0], //(String) ID
                            itemInfo[1], //(String) Name
                            itemInfo[2], //(String) Description
                            itemInfo[3].equals("false") ? false : true //(Boolean) isHidden
                    );

                    //Add item to current room
                    currentRoom.addItem(newItem);
                }else if(line.startsWith("exit:")){
                    String[] exitInfo = line.split(":")[1].trim().split(",");
                    Exit newExit = new Exit(
                            exitInfo[0], //(String) ID
                            exitInfo[1], //(String) Name
                            exitInfo[2], //(String) Description
                            exitInfo[3], //(String) Next Room ID
                            exitInfo[4].equals("false") ? false : true //(Boolean) isHidden
                    );

                    //Add exit to current room
                    currentRoom.addExit(newExit);
                }else if(line.startsWith("score:")){
                    Score playerScore;
                    String stringScore = line.split(":")[1].trim();
                    Int startingScore;
                    try{
                        startingScore = Integer.parseInt(stringScore);
                    }catch(NumberFormatException e){
                        startingScore = 0;
                    }finally{
                        playerScore = new Score(startingScore);
                        gameState.getPlayer().setScore(playerScore);
                    }
                }
            }
        } catch (java.lang.Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Game File parsing complete");

        //Initialise the new GameState object ready to return
        gameState = new GameState(
                map,
                player
        );

        return gameState;
    }
   
}
