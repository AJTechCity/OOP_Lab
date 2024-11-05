package org.uob.a1;

public class EasyGameOutputs{

    /*
        A custom class created to reduce the number of times
        the same code is written throughout the program. Also 
        provides for easy updates on regularly used strings
    */

    public static void printTitle(String title){
        System.out.println("-".repeat(20));
        System.out.println(title);
        System.out.println("-".repeat(20));
    }

    public static void printCommandHelp(){
        System.out.println(commandHelp());
    }

    public static String commandHelp(){
        String helpString = """
        ------------------------------
            Game Command Help Menu
        ------------------------------
        MOVE COMMANDS:
        Type 'move' followed by one of the below directions to move your player object:
            - 'north'
            - 'east'
            - 'south'
            - 'west'

        VIEW COMMANDS:
        Type 'view' followed by one of these nouns and information will be printed to the console:
            - 'map' -> Used to view the current map status
            - 'score' -> Used to view the player's current score
            - 'inventory' -> Used to view the player's current item inventory
        ENTER ROOM COMMANDS:
        Type 'enter room' followed by the character of a room either North, East, South, or West of your current position
            - i.e 'enter room A' to enter room with symbol 'A'
        
        Type 'quit' to exit the game and view your final score!
        """;

        return helpString;
    }

    public static void printCommandHelpErrorMessage(String command){
        //Used when the user types an invalid command in the main game loop
        System.out.println("Invalid " + command + " command. Type 'help' for available commands");
    }
}