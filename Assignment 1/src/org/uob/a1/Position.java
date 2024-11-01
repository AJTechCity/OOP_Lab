package org.uob.a1;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean moveNorth(Map m){
        if(this.y > 0){
            this.y -= 1;
            return true;
        }else{
            System.out.println("Edge of map, cannot move north");
            return false;
        }
    }

    public boolean moveSouth(Map m){
        if(this.y < m.getHeight()-1){
            this.y += 1;
            return true;
        }else{
            System.out.println("Edge of map, cannot move south");
            return false;
        }
    }

    public boolean moveEast(Map m){
        if(this.x < m.getWidth() - 1){
            this.x += 1;
            return true;
        }else{
            System.out.println("Edge of map, cannot move east");
            return false;
        }
    }

    public boolean moveWest(Map m){
        if(this.x > 0){
            this.x -= 1;
            return true;
        }else{
            System.out.println("Edge of map, cannot move west");
            return false;
        }
    }

}