package org.uob.a1;

import java.util.Scanner; 

public class Game {
    
    public static void main(String[] args) {

        Map gameMap = new Map(10,10);

        Room[] rooms = { 
            new Room("Room A", "A dusty, dirty old room", 'A', new Position(1,2)),
            new Room("Room B", "A dusty, dirty old room", 'B', new Position(4,1)),
            new Room("Room C", "A dusty, dirty old room", 'C', new Position(1,3)),
            new Room("Room D", "A dusty, dirty old room", 'D', new Position(4,5)),
            new Room("Room E", "A dusty, dirty old room", 'E', new Position(2,6)),
        };

        for(int i=0;i<rooms.length;i++){
            Room r = rooms[i];
            gameMap.placeRoom(r.getPosition(), r.getSymbol());
        }

        System.out.println(gameMap.display());
    }
    
}