public class Location{
    private String name;
    private double x;
    private double y;

    public Location(String name, double x, double y){
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return this.name + "(" + this.x + "," + this.y + ")";
    }

    public String getName(){
        return this.name;
    }

    public double getX(){
        return this.x;
    }

    public double getY(){
        return this.y;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void moveTo(double x, double y){
        this.x = x;
        this.y = y;
    }

    public double distance(Location other){
        return Math.sqrt(
            Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2)
        );
    }
}