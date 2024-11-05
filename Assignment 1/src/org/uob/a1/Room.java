package org.uob.a1;

public class Room {

    private String name;
    private String description;
    private char symbol;
    private Position position;
    private Puzzle roomPuzzle=null;
    private boolean puzzleIsSolved = false;

    public Room(String name, String description, char symbol, Position position, Puzzle roomPuzzle){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
        this.roomPuzzle = roomPuzzle;
    }

    public Room(String name, String description, char symbol, Position position){ //Alternative constructor to pass unit tests
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public char getSymbol(){
        return this.symbol;
    }

    public Position getPosition(){
        return this.position;
    }

    public void setPosition(Position position){
        this.position = position;
    }

    public String getDescription(){
        return this.description;
    }

    public void enterRoom(Player p){
        //add room entrance to player score
        System.out.println("You have entered room " + this.symbol);
        p.getScore().visitRoom();
        if(this.roomPuzzle != null){
            if(this.puzzleIsSolved){
                System.out.println("You have already solved this room's puzzle! Please try another room");
            }else{
                System.out.println("Starting puzzle...");
                //begin puzzle
                roomPuzzle.startPuzzle();
                boolean solved = roomPuzzle.getIsSolved();
                this.puzzleIsSolved = solved;
                //if puzzle is solved, set this.puzzleIsSolved to true
            }
        }else{
            System.out.println("This room currently doesn't have a puzzle and is still under development");
            System.out.println("\nRoom Description: " + this.description + "\n");
        }

        System.out.println("Exiting room");
    }
}