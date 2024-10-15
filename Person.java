public class Person{

    private String name;
    private Location location;

    public Person(String name, Location location){
        this.name = name;
        this.location = location;
    }

    public String getName(){
        return this.name;
    }

    public Location getLocation(){
        return this.location;
    }

    public String toString(){
        return this.name + " - " + this.location;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLocation(Location location){
        this.location = location;
    }

    public double distanceTo(Person other){
        return other.getLocation().distance(this.location);
    }
}