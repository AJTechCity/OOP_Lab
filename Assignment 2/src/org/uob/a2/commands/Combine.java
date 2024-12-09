package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the combine command, allowing the player to combine two of the items in their inventory.
 *
 * <p>
 * This command checks if the player possesses the specified items and, if so, removes them from their inventory
 * and adds a new combined item. If the player does not have either item, an error message is returned.
 * </p>
 */
public class Combine extends Command {

    private String item1Name;
    private String item2Name;

    private Item item1;
    private Item item2;

    public Combine(String item1, String item2){
        this.commandType = CommandType.DROP;
        this.value = item1;
        this.item1Name = item1;
        this.item2Name = item2;
    }

    public String toString(){
        return "Drop Command";
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();

        if(!player.hasItem(this.item1Name)){
            return "You do not own " + this.item1Name;
        }

        if(!player.hasItem(this.item2Name)){
            return "You do not own " + this.item2Name;
        }

        //Drop item from player and into current room
        this.item1 = player.getItem(this.item1Name);
        this.item2 = player.getItem(this.item2Name);

        //Need to check if item1 and item2 are a valid combo
        //if they are, then execute the swap

        return "You drop: " + this.value;
    }

}
