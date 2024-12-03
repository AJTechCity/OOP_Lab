package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the status command, allowing the player to retrieve information
 * about their inventory, specific items, or their overall status.
 * 
 * <p>
 * The status command can display a list of items in the player's inventory, 
 * provide details about a specific item, or show the player's general status.
 * </p>
 */
public class Status extends Command {

    private String topic;

    public Status(String topic){
        this.topic = topic;
    }

    @Override
    public String toString(){
        return "ToString";
    }

    @Override
    public String execute(GameState gameState){
        return "Execute";
    }
  
}
