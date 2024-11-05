package org.uob.a1;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void move(String direction, Map m){

        //This method works as a combination of the moveNorth, moveSouth, etc methods so that there is less code to worry about

        int newX=this.x, newY=this.y;

        /*
            We use the newX and newY variables to update what our new coordinates would be
            then we verify if they are valid coordinates based on if there is something in the way
            or if they have reached the end of the map.
        */

        switch(direction.toLowerCase()){
            case "north":
                newY -= 1;
                break;
            case "east":
                newX += 1;
                break;
            case "south":
                newY += 1;
                break;
            case "west":
                newX -= 1;
                break;
            default:
                System.out.println("You are attempting to move in an invalid direction. Please try again");
                break;
        }

        if(newX < 0 || newY < 0 || newX >= m.getWidth() || newY >= m.getHeight()){
            System.out.println("You are attempting to visit uncharted areas. Please move another direction!");
        }else if(m.getCharAtXY(newX, newY) != '\0'){
            System.out.println("A room is in your way. To enter it, type 'enter room ' followed by it's character symbol. Good Luck with the puzzle within!");
        }else if(newX == this.x && newY == this.y){ //This will only happen if an invalid direction character has been entered
            //Do not need anything to be performed now
        }else{
            this.x = newX;
            this.y = newY;
            System.out.println("You continue on your journey towards the " + direction);
        }
    }

    // public boolean moveNorth(Map m){
    //     move('N', m);
    //     if(this.y > 0){
    //         this.y -= 1;
    //         return true;
    //     }else{
    //         System.out.println("Edge of map, cannot move north");
    //         return false;
    //     }
    // }

    // public boolean moveSouth(Map m){
    //     move('S', m);
    //     if(this.y < m.getHeight()-1 && m.getCharAtXY(this.x, this.y+1) == '\0'){
    //         this.y += 1;
    //         return true;
    //     }else{
    //         System.out.println("Edge of map, cannot move south");
    //         return false;
    //     }
    // }

    // public boolean moveEast(Map m){
    //     move('E', m);
    //     if(this.x < m.getWidth() - 1){
    //         this.x += 1;
    //         return true;
    //     }else{
    //         System.out.println("Edge of map, cannot move east");
    //         return false;
    //     }
    // }

    // public boolean moveWest(Map m){
    //     move('W', m);
    //     if(this.x > 0){
    //         this.x -= 1;
    //         return true;
    //     }else{
    //         System.out.println("Edge of map, cannot move west");
    //         return false;
    //     }
    // }

}