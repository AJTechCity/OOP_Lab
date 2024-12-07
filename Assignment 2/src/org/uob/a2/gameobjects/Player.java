package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the player in the game, including their name, inventory, and equipment.
 * 
 * <p>
 * The player can carry items and equipment, interact with the game world, and perform
 * actions using their inventory or equipment.
 * </p>
 */
public class Player {

    private String name;
    private ArrayList<Item> inventory;
    private ArrayList<Equipment> equipment;

    public Player(String name){
        this.name = name;
        this.init_player();
    }

    public Player(){
        this.name = "NO_NAME";
        this.init_player();
    }

    private void init_player(){
        //Init ArrayLists
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public boolean hasItem(String itemName){
        return this.getItem(itemName) == null ? false : true;
    }

    public Item getItem(String itemName){
        for(Item item : this.inventory){
            if(item.getName().equals(itemName)){
                return item;
            }
        }
        return null;
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public void removeItem(String itemName){
        //Only to be run once the user's inventory has been checked for the item
        Item itemToRemove = this.getItem(itemName);
        if(itemToRemove != null){
            this.inventory.remove(itemToRemove);
        }else{
            System.out.println("Item not found");
        }
    }

    public void removeEquipment(String equipmentName){
        Equipment equipmentToRemove = this.getEquipment(equipmentName);
        if(equipmentToRemove != null){
            this.equipment.remove(equipmentToRemove);
        }else{
            System.out.println("Equipment not found");
        }
    }

    public ArrayList<Equipment> getEquipment(){
        return this.equipment;
    }

    public boolean hasEquipment(String equipmentName){
        return this.getEquipment(equipmentName) == null ? false : true;
    }

    public Equipment getEquipment(String equipmentName){
        for(Equipment equipItem : this.equipment){
            if(equipItem.getName().equals(equipmentName)){
                return equipItem;
            }
        }
        return null;
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public ArrayList<GameObject> getAll(){
        ArrayList<GameObject> allGameObjects = new ArrayList<GameObject>();
        allGameObjects.addAll(this.inventory);
        allGameObjects.addAll(this.equipment);
        return allGameObjects;
    }

    /**
     * Returns a string representation of the player's current state, including their name,
     * inventory, and equipment descriptions.
     *
     * @return a string describing the player, their inventory, and equipment
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder("Player Name: " + this.name + "\nInventory:\n");
        for (Item i : this.inventory) {
            out.append("- ").append(i.getDescription()).append("\n");
        }
        out.append("Equipment:\n");
        for (Equipment e : this.equipment) {
            out.append("- ").append(e.getDescription()).append("\n");
        }
        return out.toString();
    }
}
