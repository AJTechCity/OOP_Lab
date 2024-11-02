package org.uob.a1;

public class Map {

    final private char EMPTY='.';
    private int width, height;
    private char[][] mapArray;

    public Map(int width, int height){
        this.width = width;
        this.height = height;

        mapArray = new char[height][width];
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                mapArray[i][j] = this.EMPTY;
            }
        }
    }

    public char getCharAtXY(int x, int y){
        if(x >=0 && y>=0 && x< this.width && y < this.height){
            return mapArray[y][x];
        }else{
            return EMPTY;
        }
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public char[][] getMapArray(){
        return this.mapArray;
    }

    public char[] getNESWRadius(Position p){
        int x = p.x, y=p.y;
        char[] neswRadius = new char[4];
        neswRadius[0] = getCharAtXY(p.x, p.y-1); //NORTH Char
        neswRadius[1] = getCharAtXY(p.x+1, p.y); //EAST Char
        neswRadius[2] = getCharAtXY(p.x, p.y+1); //SOUTH Char
        neswRadius[3] = getCharAtXY(p.x-1, p.y); //WEST Char


        return neswRadius;
    }

    public void placeRoom(Position pos, char symbol){
        //Get position x and y (public ints) and then set that co-ordinate within the 2D array equal to the symbol provided
        mapArray[pos.y][pos.x] = symbol;
    }

    public void placeCharacter(Character c, char symbol){
        mapArray[c.getPosition().y][c.getPosition().x] = symbol;
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

    public String display(Player p){
        String mapString;
        Position pos = p.position;

        if(mapArray[pos.y][pos.x] == EMPTY){
            mapArray[pos.y][pos.x] = 'p';
            mapString = this.display();
            mapArray[pos.y][pos.x] = EMPTY;
        }else{
            mapString = this.display();
        }
        
        
        return mapString;
    }

    public String toString(){
        return this.display();
    }


}