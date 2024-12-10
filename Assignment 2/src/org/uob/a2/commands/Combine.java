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

    private void loadItemObjects(Player player){
        item1 = player.getItem(item1Name);
        item2 = player.getItem(item2Name);
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();

        if(item1Name == null || item2Name == null) return "Invalid Combination";

        if(!player.hasItem(this.item1Name)){
            return "You do not own " + this.item1Name;
        }

        if(!player.hasItem(this.item2Name)){
            return "You do not own " + this.item2Name;
        }

        loadItemObjects(player); //Load the Item Objects into item1 and item2 variables

        //Locate the combination Object
        Combination combination = gameState.findCombination(item1, item2);

        if(combination == null) return "Invalid Combination";
        if(combination.isCombinationUsed() == true) return "Combination already used";

        //Combination is valid, found, and not used, we can combine the items and give player new equipment
        player.removeItem(item1Name);
        player.removeItem(item2Name);
        combination.use(); //Mark combination as used so it cannot be used again in future
        player.addEquipment(combination.getCombinedItem());
        return "You combined " + item1Name + " with " + item2Name + " and create " + combination.getCombinedItem().getName();
    }

}
