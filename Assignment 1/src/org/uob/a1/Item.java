package org.uob.a1;

public class Item{
    private String name;
    private String description;
    private boolean isUsable;

    public Item(String name, String description, boolean isUsable){
        this.name = name;
        this.description = description;
        this.isUsable = isUsable;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean isUsable(){
        return this.isUsable;
    }
}