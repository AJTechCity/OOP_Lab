package org.uob.a2.gameobjects;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.nio.file.*;

import org.uob.a2.utils.*;

/**
 * Represents the game map, which consists of a collection of rooms and the current room the player is in.
 * 
 * <p>
 * The map allows for navigation between rooms, adding new rooms, and managing the current room context.
 * </p>
 */
public class Map {

    private int width, height;
    private char mapArray[][];
    private ArrayList<Room> rooms;
    private String currentRoomId;

    public Map(){
        this.width = 20;
        this.height = 20;

        mapArray = new char[height][width];
        this.rooms = new ArrayList<>();
    }

    public Room getCurrentRoom(){
        return getRoomById(this.currentRoomId);
    }

    public void addRoom(Room room){
        rooms.add(room);
    }

    public void setCurrentRoom(String roomId){
        this.currentRoomId = roomId;
    }

    public Room getRoomById(String roomId){
        for(Room room : rooms){
            if(room.id == roomId){
                return room;
            }
        }
        return null;
    }
  
    /**
     * Returns a string representation of the map, including all rooms.
     *
     * @return a string describing the map and its rooms
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Map:\n");
        for (Room r : this.rooms) {
            out.append(r.toString()).append("\n");
        }
        return out.toString();
    }
}

