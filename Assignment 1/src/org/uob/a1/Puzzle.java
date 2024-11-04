package org.uob.a1;

class Puzzle{
    //Generic class for puzzles - not used extensively right now
    private String name;
    private String description;
    private String prizeItem;
    private Player puzzleSolver;
    
    public Puzzle(String name, String description, String prizeItem, Player puzzleSolver){
        this.name = name;
        this.description = description;
        this.prizeItem = prizeItem;
        this.puzzleSolver = puzzleSolver;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getPrizeItem(){
        return this.prizeItem;
    }

    


    public void startPuzzle(){
        System.out.println("Puzzle logic not defined");
    }

    public puzzleSolved(){
        System.out.println("Congratultions " + player.getName() + "! You have solved the " + this.name + " puzzle. ")

    }
}