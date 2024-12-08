package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents a generic game object that can be part of the game world.
 * 
 * <p>
 * Game objects have a name, description, unique identifier, and visibility state.
 * This abstract class serves as a base for more specific types of game objects.
 * </p>
 */
public abstract class GameObject {
    /**
     * Returns a string representation of the game object, including its ID, name,
     * description, and visibility state.
     *
     * @return a string describing the game object
     */

    protected String name;
    protected String description;
    public String id;
    protected boolean hidden;
    public String gameObjectYAMLType;

    public GameObject(String id, String name, String description, boolean hidden){
        this.id = id;
        this.name = name;
        this.description = description;
        this.hidden = hidden;
    }

    public GameObject(){
        this.id = "NO_ID";
        this.name = "NO_NAME";
        this.description = "NO_DESC";
        this.hidden = false;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public String getId(){
        return this.id;
    }

    public void setHidden(boolean hidden){
        this.hidden = hidden;
    }

    public boolean getHidden(){
        return this.hidden;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public abstract String toYAML();

    @Override
    public String toString() {
        return "GameObject {" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", hidden=" + hidden +
               '}';
    }
}
