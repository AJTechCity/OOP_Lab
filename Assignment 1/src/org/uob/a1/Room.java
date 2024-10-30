package org.uob.a1;

public class Room {

    private String name;
    private String description;
    private char symbol;
    private Position position;

    public Room(String name, String description, char symbol, Position position){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public Position getPosition(){
        return this.position;
    }

    public String getDescription(){
        return this.description;
    }
}