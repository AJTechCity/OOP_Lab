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
    
    private ArrayList<Room> rooms;
    private String currentRoomId;

    public Map(){
        this.rooms = new ArrayList<>();
    }

    public String getCurrentRoomId(){
        return this.currentRoomId;
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

    public ArrayList<Room> getRooms(){
        return rooms;
    }

    public Room getRoomById(String roomId){
        roomId = roomId.trim();
        for(Room room : rooms){
            if(room.getId().equals(roomId)){
                return room;
            }
        }
        return null;
    }

    public String renderMap(){
        return "Rendered Map";
    }

    public String toYAML(){
        return this.currentRoomId;
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

