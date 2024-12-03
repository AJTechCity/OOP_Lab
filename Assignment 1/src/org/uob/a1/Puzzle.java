package org.uob.a1;

class Puzzle{
    //Generic class for puzzles - includes the basic logic of the class, required variables and methods
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

    public Player getPuzzleSolver(){ //Gets the person Object - used to update scores or give items
        return this.puzzleSolver;
    }

    public boolean getIsSolved(){ //Used to check if user can solve the puzzle when entering a room
        return this.isSolved;
    }


    public void startPuzzle(){
        /*
            Used in debugging to signal no valid puzzle class
            extension has been used and, hence, no valid game logic
            is available
        */
        System.out.println("Puzzle logic not defined");
    }

    public void puzzleSolved(){
        //Run once puzzle is solved
        //Used to update required variables, give items to user
        //and output a success message
        this.isSolved = true;
        this.puzzleSolver.getScore().solvePuzzle();
        this.puzzleSolver.getInventory().addItem(this.prizeItem);
        System.out.println("Congratultions " + this.puzzleSolver.getName() + "! You have solved the " + this.name + " puzzle. You score has been updated and your prize of '" + this.prizeItem + "' has been added to your inventory. Use the 'view inventory' command to view it. \n\nTeleporting you out of the room...");
    }
}