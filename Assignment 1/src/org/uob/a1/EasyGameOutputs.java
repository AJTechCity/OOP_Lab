package org.uob.a1;

public class EasyGameOutputs{
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
            - 'map'
        """;

        return helpString;
    }
}