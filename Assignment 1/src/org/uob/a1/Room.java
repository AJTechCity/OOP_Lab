package org.uob.a1;

import java.util.Scanner;

public class Room {

    private String name;
    private String description;
    private char symbol;
    private Position position;
    private Puzzle roomPuzzle=null;
    private boolean puzzleIsSolved = false;

    private Scanner scanner = new Scanner(System.in);

    public Room(String name, String description, char symbol, Position position, Puzzle roomPuzzle){
        this.name = name;
        this.description = description;
        this.symbol = symbol;
        this.position = position;
        this.roomPuzzle = roomPuzzle;
    }

    public Room(String name, String description, char symbol, Position position){
        /*
            Alternative constructor to pass unit tests as they don't
            pass a 'Puzzle' parameter
        */
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

        //Run when user types the 'enter room' command
        //Increases their score's room visit count
        //Tells them whether the puzzle is available to be solved or not
        //Automatically 'exits' the room after certain exit flags are given

        System.out.println("You have entered room " + this.symbol + " - The " + this.name);
        System.out.println("\nRoom Description: " + this.getDescription() + "\n");
        System.out.print("Press enter to move onto the puzzle... ");
        scanner.nextLine();
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