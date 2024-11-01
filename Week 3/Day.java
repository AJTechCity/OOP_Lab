public class Day{
    private int day;
    private int month;
    private int year;

    public Day(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    

    public String toString(){
        return (this.day + "/" + this.month + "/" + this.year);
    }

    public boolean newYearsDay(){
        return (day == 1) && (month == 1);
    }

    public boolean isCentury(){
        return (year % 100 == 0);
    }

    public boolean isLeapYear(){
        return (year % 4 == 0);
    }

    public String dayOfWeek(){

        String result = "unknown";

        //century
        int century = year / 100;
        int centuryItem = 2 * (3-(century%4));
        
        //year

        int yearPart = year % 100;
        int x = yearPart/12;
        int y = yearPart % 12;
        int z = y/4;
        
        int yearItem = x + y + z;

        //month
        int[] monthScore = {0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 4, 5};
        int monthItem = monthScore[month-1];

        //day
        int dayItem = day;
        
        if(this.isLeapYear() && (month == 1 || month == 2)){
            dayItem = day + 6;
        }else{
            dayItem = day;
        }

        int total = (dayItem + monthItem + yearItem + centuryItem)%7;

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

        result = days[total];

        return result;
    }

    /* Week 3 Extra Practice - Implement a function which calculates the day of easter for the given Day Object's Year value and return it as a new Day object */

    public Day easterDay(){
        
    }
}