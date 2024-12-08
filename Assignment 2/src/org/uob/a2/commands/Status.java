package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;
import java.util.ArrayList;

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
        this.commandType = CommandType.STATUS;
        this.topic = topic;
    }

    @Override
    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        StringBuilder out = new StringBuilder("");
        if(this.topic.equals("inventory")){
            out.append("Player Inventory\n");
            for(GameObject obj : player.getAll()){
                out.append("- " + obj.getName() + "\n");
            }
        }else if(this.topic.equals("player")) {
            out.append(player.toString());
        }else if(this.topic.equals("map")){
            out.append(gameState.getMap().renderMap());
        }else if(this.topic.equals("score")){
            out.append("Player score: " + player.getScore().getScore());
        }else{
            //Check if topic matches item name - display description
            //if not display an error
            for(GameObject obj : player.getAll()){
                if(obj.getName().equals(this.topic)){
                    out.append(obj.getDescription());
                }
            }
        }

        return out.toString();
    }

    @Override
    public String toString(){
        return "Status {\n" +
                "Topic: " + this.topic + "\n}";
    }
  
}
