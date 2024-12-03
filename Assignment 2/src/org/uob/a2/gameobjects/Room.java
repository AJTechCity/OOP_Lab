package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a room in the game, which is a type of {@code GameObject}.
 * 
 * <p>
 * Rooms can have items, equipment, features, and exits. They also manage navigation
 * and interactions within the game world.
 * </p>
 */
public class Room extends GameObject {

    private ArrayList<Item> items;
    private ArrayList<Equipment> equipment;
    private ArrayList<Feature> features;
    private ArrayList<Exit> exits;

    public Room(String id, String name, String description, boolean hidden){
        super(id, name, description, hidden);
    }

    public Room(){}

    @Override
    public void setName(String name){
        //Sets name of room - Can be removed as exists in parent class?
        this.name = name;
    }

    @Override
    public void setDescription(String description){
        //Sets description of room - Can be removed as exists in parent class?
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public ArrayList<Exit> getExits(){
        //Retrieves the exits of the room
        return this.exits;
    }

    public void addExit(Exit exit){
        this.exits.add(exit);
    }

    public ArrayList<Item> getItems(){
        return this.items;
    }

    public Item getItem(String id){
        return findGameObjectById(items, id);
    }

    public Item getItemByName(String name){
        return findGameObjectByName(items, name);
    }

    public Feature getFeatureByName(String name){
        return findGameObjectByName(features, name);
    }

    public ArrayList<Equipment> getEquipments(){
        return this.equipment;
    }

    public Equipment getEquipment(String id){
        return findGameObjectById(equipment, id);
    }

    public Exit getExit(String id){
        return findGameObjectById(exits, id);
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public Feature getFeature(String id){
        return findGameObjectByName(features, id);
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public ArrayList<Feature> getFeatures(){
        return this.features;
    }

    public ArrayList<GameObject> getAll(){
        ArrayList<GameObject> allGameObjects = new ArrayList<GameObject>();
        allGameObjects.addAll(this.items);
        allGameObjects.addAll(this.equipment);
        allGameObjects.addAll(this.features);
        allGameObjects.addAll(this.exits);

        return allGameObjects;
    }

    public void addFeature(Feature feature){
        this.features.add(feature);
    }

    public boolean hasItem(String itemName){
        return findGameObjectByName(items, itemName) == null ? false : true;
    }

    public boolean hasEquipment(String name){
        return findGameObjectByName(items, name) == null ? false : true;
    }

    /**
     * Returns a string representation of the room, including its contents and description.
     *
     * @return a string describing the room
     */
    @Override
    public String toString() {
        String out = "[" + id + "] Room: " + name + "\nDescription: " + description + "\nIn the room there is: ";
        for (Item i : this.items) {
            out += i + "\n";
        }
        for (Equipment e : this.equipment) {
            out += e + "\n";
        }
        for (Feature f : this.features) {
            out += f + "\n";
        }
        for (Exit e : this.exits) {
            out += e + "\n";
        }
        return out + "\n";
    }
}
