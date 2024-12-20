package org.uob.a1;

public class Score {

    //The basic Score class based off of tasks provided within the Assignment specification

    private final int PUZZLE_VALUE=10;

    private int startingScore=0;
    private int currentScore=0;
    private int roomsVisited=0;
    private int puzzlesSolved=0;
    private int scoreDeductions=0;

    //All methods updating variables should update the currentScore variable

    public Score(int startingScore){
        //Should set starting score and call updateScore method

        this.startingScore = startingScore;
        this.updateScore();
    }

    public void visitRoom(){
        //Should increase the roomsVisited variable by 1, then update the current score
        this.roomsVisited++;
        this.updateScore();
    }

    public void solvePuzzle(){
        //Should increase puzzlesSolved by 1 and increase the current score by the PUZZLE_VALUE, then update the current score

        this.puzzlesSolved++;
        this.currentScore += this.PUZZLE_VALUE;

        this.updateScore();
    }

    public void reduceScore(int amount){
        this.scoreDeductions += amount;
        this.updateScore();
    }

    public double getScore(){
        //should get the value of currentScore
        return this.currentScore;
    }

    private void updateScore(){
        //Run every time a variable is changed so that the score is always correct
        this.currentScore = this.startingScore - this.roomsVisited + (this.puzzlesSolved * this.PUZZLE_VALUE) - this.scoreDeductions;
    }
}