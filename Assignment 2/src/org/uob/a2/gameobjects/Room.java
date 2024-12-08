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

        //Init ArrayLists
        this.items = new ArrayList<>();
        this.equipment = new ArrayList<>();
        this.features = new ArrayList<>();
        this.exits = new ArrayList<>();
    }

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
        for(Item item: this.items){
            if(item.getName().equals(name)){
                return item;
            }
        }
        return null;
    }

    public Feature getFeatureByName(String name){
        for(Feature feature : this.features){
            if(feature.getName().equals(name)){
                return feature;
            }
        }
        return null;
    }

    public ArrayList<Equipment> getEquipments(){
        return this.equipment;
    }

    public Equipment getEquipment(String id){
        for(Equipment equip: this.equipment){
            if(equip.getId().equals(id)){
                return equip;
            }
        }
        return null;
    }

    public Equipment getEquipmentByName(String name){
        for(Equipment equip: this.equipment){
            if(equip.getName().equals(name)){
                return equip;
            }
        }
        return null;
    }

    public Exit getExit(String id){
        for(Exit exit: this.exits){
            if(exit.getId().equals(id)){
                return exit;
            }
        }
        return null;
    }

    public Exit getExitByName(String exitName){
        return this.exits.stream().filter(exit->exit.getName().equals(exitName)).findFirst().orElse(null);
    }

    public void addEquipment(Equipment equipment){
        this.equipment.add(equipment);
    }

    public void removeEquipment(Equipment equipment){
        this.equipment.remove(equipment);
    }

    public Feature getFeature(String id) {
        for (Feature feature : this.features) {
            if (feature.getId().equals(id)) {
                return feature;
            }
        }
        return null;
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        if(this.hasItem(item.getName())){
            this.items.remove(item);
        }
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
        return this.getItemByName(itemName) == null ? false : true;
    }

    public boolean hasEquipment(String name){
        return this.getEquipmentByName(name) == null ? false : true;
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
