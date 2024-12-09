package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a valid combination of two objects and their result
 *
 * <p>
 * The combination is stored in the gameState and can be called by the Combine Command to validate the players
 * request to combine two items together
 * </p>
 */
public class Combination {

    private String item1Name;
    private String item2Name;
    private Equipment combinedItem;
    private Boolean combinationUsed;

    public Combination(String item1Name, String item2Name, Equipment combinedItem) {
        this.item1Name = item1Name;
        this.item2Name = item2Name;
        this.combinedItem = combinedItem;
        this.combinationUsed = false;
    }

    public String getItem1Name(){
        return item1Name;
    }

    public String getItem2Name(){
        return item2Name;
    }

    public Equipment getCombinedItem(){
        return this.combinedItem;
    }

    public boolean isCombinationUsed(){
        return combinationUsed;
    }

    public boolean isValidCombinationItemNames(String name1, String name2){
        if(item1Name.equals(name1) && item2Name.equals(name2) || item1Name.equals(name2) && item2Name.equals(name1)){
            return true;
        }else{
            return false;
        }
    }

    public boolean isValidCombinationItems(Item item1, Item item2){
        if(item1.getId() == item2.getId()) return false;
        if(item1.getName() == item2.getName()) return false;

        if(item1.getHidden() == false && item2.getHidden() == false){
            return isValidCombinationItemNames(item1.getName(), item2.getName());
        }else return false;
    }

    public void use(){
        this.combinationUsed = true;
    }

    public String toYAML(){
        if(this.combinationUsed) return null;
        return this.item1Name + "," + this.item2Name + "," + combinedItem.toYAML();
    }

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Combination Item 1: " + this.item1Name + "\nCombination Item 2: " + this.item2Name + "\n");
        out.append("Combined Item: " + this.combinedItem.toString() + "\n");
        return out.toString();
    }
}
