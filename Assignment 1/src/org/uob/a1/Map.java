package org.uob.a1;

public class Map {

    final private char EMPTY='.';
    private int width, height;
    private char[][] mapArray;

    public Map(int width, int height){
        this.width = width;
        this.height = height;

        mapArray = new char[width][height];
    }

    public void placeRoom(Position pos, char symbol){
        //Get position x and y (public ints) and then set that co-ordinate within the 2D array equal to the symbol provided
        mapArray[pos.x][pos.y] = symbol;
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                mapArray[i][j] = this.EMPTY;
            }
        }
    }

    public String display(){
        //Run a nested loop that loops over the width and height of array and prints the desired item
        String mapString=""; //String to be returned at the end of the method
        for(int i = 0; i<this.height; i++){
            for(int j=0;j<this.width;j++){
                mapString += mapArray[i][j]; //Add chars to current line of the mapString variable
            }
            mapString += "\n"; //Append a new line so the next row is on the next line
        }

        return mapString;
    }


}