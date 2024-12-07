package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the get command, allowing the player to pick up an item from the current room and add it to their inventory.
 * 
 * <p>
 * This command checks if the specified item is present in the current room. If the item exists and the player
 * does not already have it, the item is added to the player's inventory and removed from the room. Otherwise,
 * an appropriate message is returned.
 * </p>
 */
public class Get extends Command {

    private String item;

    public Get(String item){
        this.commandType = CommandType.GET;
        this.item = item; //Name of item to pick up
    }

    public String toString(){
        return "Get";
    }

    public String execute(GameState gameState){
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Player player = gameState.getPlayer();
        Item pickupItem = currentRoom.getItemByName(this.item);
        Equipment pickupEquipment = currentRoom.getEquipmentByName(this.item);
        if(pickupItem != null && pickupEquipment == null){
            if(!player.hasItem(this.item)){
                player.addItem(pickupItem); //add item to player inventory
                currentRoom.removeItem(pickupItem); //remove item from current room
                return "You pick up: " + this.item;
            }else{
                return "You already have " + this.item;
            }
        }else if(pickupEquipment != null && pickupItem == null){
            if(!player.hasEquipment(this.item)){
                player.addEquipment(pickupEquipment); //add item to player inventory
                currentRoom.removeEquipment(pickupEquipment); //remove item from current room
                return "You pick up: " + this.item;
            }else{
                return "You already have " + this.item;
            }
        }else{
            return "No " + this.item + " to get.";
        }
    }
   
}
