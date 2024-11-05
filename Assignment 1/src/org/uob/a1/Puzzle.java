package org.uob.a1;

class Puzzle{
    //Generic class for puzzles - not used extensively right now
    private String name;
    private String description;
    private String prizeItem;
    private Player puzzleSolver;
    private boolean isSolved = false;
    
    public Puzzle(String name, String description, String prizeItem, Player puzzleSolver){
        this.name = name;
        this.description = description;
        this.prizeItem = prizeItem;
        this.puzzleSolver = puzzleSolver;
    }

    public Puzzle(){ //Used to pass unit tests
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

    public Player getPuzzleSolver(){
        return this.puzzleSolver;
    }

    public boolean getIsSolved(){
        return this.isSolved;
    }


    public void startPuzzle(){
        System.out.println("Puzzle logic not defined");
    }

    public void puzzleSolved(){
        this.isSolved = true;
        this.puzzleSolver.getScore().solvePuzzle();
        this.puzzleSolver.getInventory().addItem(this.prizeItem);
        System.out.println("Congratultions " + this.puzzleSolver.getName() + "! You have solved the " + this.name + " puzzle. You score has been updated and your prize of '" + this.prizeItem + "' has been added to your inventory. Use the 'view inventory' command to view it. \n\nTeleporting you out of the room...");
    }
}