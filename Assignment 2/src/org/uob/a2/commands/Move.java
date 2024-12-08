package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the move command, allowing the player to navigate to a different room in the game world.
 * 
 * <p>
 * The move command checks if the specified direction corresponds to an available exit in the current room.
 * If a matching exit is found, the player's location is updated to the connected room.
 * </p>
 */
public class Move extends Command {

    private String direction;

    public Move(String direction){
        this.commandType = CommandType.MOVE;
        this.direction = direction;
    }

    public String execute(GameState gameState){
        StringBuilder out = new StringBuilder("");
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Exit exit = currentRoom.getExitByName(this.direction);
        //this.direction is the name of the exit
        if(exit != null){ //If exit ID is valid and finds an exit object
            if(exit.getHidden() == false){//If exit is visible to user
                out.append("Moving towards " + this.direction + "\n");
                //Custom: Increase the number of rooms visited in the Score Object
                gameState.getPlayerScore().visitRoom();
                //Use the Map object to advance user to next room
                gameState.getMap().setCurrentRoom(exit.getNextRoom());
            }else{
                out.append("No exit found in that direction.");
            }
        }else{
            out.append("No exit found in that direction.");
        }
        return out.toString();
    }

    public String toString(){
        return "Move " + this.direction;
    }
}
