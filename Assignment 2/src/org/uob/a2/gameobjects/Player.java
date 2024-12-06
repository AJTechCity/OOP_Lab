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

        //Init ArrayLists
        this.inventory = new ArrayList<>();
        this.equipment = new ArrayList<>();
    }

    public Player(){
        this.name = "NO_NAME";
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Item> getInventory(){
        return this.inventory;
    }

    public boolean hasItem(String itemName){
//        return findGameObjectByName(inventory, name) == null ? false : true;
        return false;
    }

    public Item getItem(String itemName){
//        return findGameObjectByName(inventory, name);
        return null;
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public ArrayList<Equipment> getEquipment(){
        return this.equipment;
    }

    public boolean hasEquipment(String equipmentName){
//        return findGameObjectByName(equipment, equipmentName) == null ? false : true;
        return false;
    }

    public Equipment getEquipment(String equipmentName){
//        return findGameObjectByName(equipment, equipmentName);
        return null;
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
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
