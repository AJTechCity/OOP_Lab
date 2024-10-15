public class Main{
    public static void main(String[] args){
        Location uni = new Location(
            "University of Birmingham",
            2.45,
            100.3
        );

        Location accom = new Location(
            "Accomodation",
            100.94,
            674.3
        );

        Person a = new Person(
            "Person A",
            accom
        );

        Person b = new Person(
            "Person B",
            uni
        );

        System.out.println(a.distanceTo(b));
    }
}