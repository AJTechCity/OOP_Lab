package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the drop command, allowing the player to drop an item from their inventory into the current room.
 * 
 * <p>
 * This command checks if the player possesses the specified item and, if so, removes it from their inventory
 * and adds it to the current room. If the player does not have the item, an error message is returned.
 * </p>
 */
public class Drop extends Command {

    public Drop(String item){
        this.commandType = CommandType.DROP;
        this.value = item;
    }

    public String toString(){
        return "Drop Command";
    }

    public String execute(GameState gameState){
        //Method needs to use GameState Obj to access Player Obj.
        //Then needs to check if user has the item
        //If has the item, run the Player.removeItem() method
        //Once dropped, return string telling user they dropped the item
        //If doesn't have item, return a string telling the user they do not own the item
        Player player = gameState.getPlayer();
        if(player.hasItem(this.value)){
            player.removeItem(this.value);
            return "You have dropped the item '" + this.value + "'";
        }else{
            return "You do not own the item '" + this.value + "'";
        }
    }
   
}
