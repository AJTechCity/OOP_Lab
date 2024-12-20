package org.uob.a2.gameobjects;

/**
 * Represents an exit in the game, allowing the player to move from one room to another.
 * 
 * <p>
 * Exits have a destination (next room), a description, and can be hidden or visible based on game logic.
 * </p>
 */
public class Exit extends GameObject {


    private String nextRoom; //Used to Store ID Of the next room connected to this exit

    public Exit(String id, String name, String description, String nextRoom, boolean hidden){
        super(id, name, description, hidden);
        this.nextRoom = nextRoom;
        this.gameObjectYAMLType = "exit";
    }

    public String getNextRoom(){
        return this.nextRoom;
    }

    public String toYAML(){
        return this.id + "," + this.name + "," + this.description + "," + this.nextRoom + "," + (this.hidden==false ? "false" : "true");
    }

    /**
     * Returns a string representation of the exit, including attributes inherited from {@code GameObject}
     * and the identifier of the next room.
     *
     * @return a string describing the exit
     */
    @Override
    public String toString() {
        return super.toString() + ", nextRoom=" + nextRoom;
    }
}
