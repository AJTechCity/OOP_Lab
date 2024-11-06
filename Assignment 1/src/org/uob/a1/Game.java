package org.uob.a1;

import java.util.Scanner; 

public class Game {

    private static Map gameMap = new Map(10,20);
    private static EasyGameOutputs easyGameOutputs = new EasyGameOutputs(); //Custom class to reduce number of lines written when outputting the same messages
    private static Scanner scanner = new Scanner(System.in);
    private static String command = "";
    private static Player player = new Player("", new Position(0,0));

    //PUZZLES
    //Future iterations of the code puzzle will use randomly generated 6 digit numbers
    private static Puzzle codePuzzle1 = new CodePuzzle("key_B", player, "987851"); //Done
    private static Puzzle codePuzzle2 = new CodePuzzle("key_E", player, "651227"); //Done
    private static Puzzle codePuzzle3 = new CodePuzzle("sword", player, "123456"); //Done
    private static Puzzle codePuzzle4 = new CodePuzzle("key_K", player, "325648"); //Done

    private static Puzzle keyPuzzle1 = new KeyPuzzle("key_B", "knowledge", "Your prize item ('knowledge') is required to solve any of the math-based puzzles - Don't drop it!", player); //Done
    private static Puzzle keyPuzzle2 = new KeyPuzzle("key_E", "shield", "Your prize item ('shield') is used to lower the damage taken when attacked by enemies", player); //Done
    private static Puzzle keyPuzzle3 = new KeyPuzzle("key_H", "health_potion", "Your prize item ('health_potion') is used to increase you health by 30 points when it is low", player); //Done
    private static Puzzle keyPuzzle4 = new KeyPuzzle("key_K", "trophy", "Your prize item ('trophy') is given to show you have completed the game! Congratulatons!", player); //Done

    //Future iterations of the Math Puzzle will use equation generators (custom-made) to handle auto question creation and reduce the amount of code being written
    private static Puzzle mathPuzzle1 = new MathPuzzle(
        new String[] {
            "What is 5+5",
            "What is 5 * 5",
            "What is 250 / 50",
            "What is 4 + 5 * 2 - 3",
            "What is 8 + 5 * 3"
        },
        new int[] {
            10, 25, 5, 11, 23
        },
        "math_prize_1",
        "This serves as a medal to show your accomplish within the first math puzzle of the game!",
        player
    );

    private static Puzzle mathPuzzle2= new MathPuzzle(
        new String[] {
            "What is 30% of 10",
            "What is 10% of 50",
            "How many minutes are there between 9:30AM and 2:45PM",
            "What is 15 * 4 - (36 / 3) + 8",
            "What is the sum of the first 3 square numbers (excluding 0)"
        },
        new int[] {
            3, 5, 315, 56, 14
        },
        "math_prize_2",
        "This serves as a medal to show your accomplish within the second math puzzle of the game!",
        player
    );

    private static Puzzle mathPuzzle3 = new MathPuzzle(
        new String[] {
            "What is the poduct of all the numbers on a phone keypad",
            "If a train travels 120km 3 hours, how many km's does it travel in 7 hours at the same speed",
            "What is the sum of the first 6 positive consecutive odd numbers",
            "What is ",
            "What is "
        },
        new int[] {
            0, 280, 36, 0, 0
        },
        "math_prize_3",
        "This serves as a medal to show your accomplish within the third math puzzle of the game!",
        player
    );


    //ROOMS
    private static Room RoomA = new Room("The Beginning Combination", "A rusty padlock blocks you from entering, you will need to crack this to gain your prize", 'A', new Position(2,1), codePuzzle1);
    private static Room RoomB = new Room("The Lone Chest", "A chest lay in the corner of the room. You will need to complete the key puzzle to unlock it...", 'B', new Position(0,3), keyPuzzle1);
    private static Room RoomC = new Room("Room C", "A metal lock bounces off a small chest containing a key. Crack it to claim your prize.", 'C', new Position(2,4), codePuzzle2);
    private static Room RoomD = new Room("Room D", "A dusty, dirty old room", 'D', new Position(3,8), mathPuzzle1);
    private static Room RoomE = new Room("Room E", "A dusty safe is in the middle of the room. It requires a key to be opened and reveal a prize.", 'E', new Position(0,6), keyPuzzle2);
    private static Room RoomF = new Room("Room F", "A dusty, dirty old room", 'F', new Position(4,1), codePuzzle3);
    private static Room RoomG = new Room("Room G", "A dusty, dirty old room", 'G', new Position(7,11), mathPuzzle2);
    private static Room RoomH = new Room("Room H", "A dusty, dirty old room", 'H', new Position(6,3), keyPuzzle3);
    private static Room RoomI = new Room("Room I", "A dusty, dirty old room", 'I', new Position(5,7), mathPuzzle3);
    private static Room RoomJ = new Room("Room J", "A dusty, dirty old room", 'J', new Position(3,13), codePuzzle4);
    private static Room RoomK = new Room("Room K", "A dusty, dirty old room", 'K', new Position(8,17), keyPuzzle4);
    private static Room[] rooms;


    //ENEMIES - Future game iteration
    // private static Enemy weaklingBandit = new Enemy("Weakling Bandit", "Beware the weakling bandit, a grandson of the Brutal Ogre. He serves as a knight to the Ogre and defends itself with an attack. He has 40 health, no protection, and a broken wooden sword. A weak enemy. Defeating him provides you with a protection potion to increase your protection", 40, 0, 7, new Position(0,4));
    // private static Enemy theMite = new Enemy("The Mite", "Beware The Mite, a grandson of the Brutal Ogre. He mystically mutated and gained protection spells and a metal sword. He has 35 health, 20 protection, and will attack when provoked. Upon defeating him, his protection spell oozes out and becoms available to you", 35, 20, 15, new Position(17,1));
    // private static Enemy e3 = new Enemy("Enemy 3", "dirty enemy 3", 10, 0, 10, new Position(11,7));
    // private static Enemy[] enemies;s
    
    public static void main(String[] args) {
        welcomeScreen(); //Runs the welcome screen things and won't advance until user presses enter
        userInfoSetup();
        gameInit();

        do{
            System.out.print("> ");
            command = scanner.nextLine();
            parseCommand(command);
        }while(!command.equals("quit"));

        System.out.println("Game over - user quit the experience");
        System.out.println("Final score was: " + player.getScore().getScore());
    }

    private static void welcomeScreen(){
        easyGameOutputs.printTitle("      THE MAP      ");
        System.out.println("Welcome to 'THE MAP' - a thrilling text-based adventure game.");
        System.out.print("Press Enter to continue to user information setup...");
        scanner.nextLine();
    }

    private static void userInfoSetup(){
        easyGameOutputs.printTitle("USER INFORMATION SETUP");
        System.out.print("Please enter your name: ");
        String nameInput = scanner.nextLine();
        player.setName(nameInput);

        System.out.println("Welcome to the game " + player.getName());
        System.out.println("Type 'help' at any time to view the list of available commands");  
        System.out.println("Type 'quit' at any time to exit the game");
    }

    private static void gameInit(){
        rooms = new Room[] { //Create array with all rooms within it
            RoomA,
            RoomB,
            RoomC,
            RoomD,
            RoomE,
            RoomF,
            RoomG,
            RoomH,
            RoomI,
            RoomJ,
            RoomK
        };

        for(int i=0;i<rooms.length;i++){ //Loop over rooms array so that they can be added to map programatically
            Room r = rooms[i];
            gameMap.placeRoom(r.getPosition(), r.getSymbol());
        }

        //For a future game iteration

        // enemies = new Enemy[] { //Create array with enemies in it
        //     weaklingBandit,
        //     theMite,
        //     e3
        // };

        // for(int i=0;i<enemies.length;i++){ //Loop over rooms array so that they can be added to map programatically
        //     Enemy e = enemies[i];
        //     gameMap.placeCharacter(e, 'e');
        // }
    }

    public static void parseCommand(String c){
        /*
            Contains all logic to read in a command from the user,
            run the required validations, and execute the commands
        */

        String[] commandParts = command.split(" ");
        String action = commandParts[0].toLowerCase(); //So no formatting issues arise when checking the command

        if(action.equals("help")){
            easyGameOutputs.printCommandHelp();
            return;
        }else if(commandParts.length == 3 && action.equals("enter")){
            if(commandParts[1].equals("room") && commandParts[2].length() == 1){
                char roomSymbol = commandParts[2].toUpperCase().charAt(0);
                char[] neswRadius = gameMap.getNESWRadius(player.getPosition());
                boolean found = false;
                for(int i=0; i<neswRadius.length; i++){
                    if(neswRadius[i] == roomSymbol){
                        found = true;
                        break;
                    }
                }

                if(found){
                    //Use logic to 'enter' the room / solve a puzzle
                    int roomIndex = roomSymbol - 'A'; //If we subtract the room symnol of A from the inputted symbol, we will end up with an index so we can get the room from our rooms array
                    rooms[roomIndex].enterRoom(player); //Call the "Enter Room" method
                }else{
                    System.out.println("Room '" + roomSymbol + "' cannot be entered as it isn't North, East, South, or West of your current position.");
                }
            }else{
                System.out.println("Invalid Command");
            }
            return;
        }else if(commandParts.length > 1){
            commandParts[1] = commandParts[1].toLowerCase();
        }
        

        switch(action.toLowerCase()){
            case "score":
                System.out.println("Please use the 'view score' command to view your score. Use the 'help' command for more information on game commands");
                break;
            case "look":
                if(commandParts.length == 2){
                    String itemName = commandParts[1];
                    boolean found = false;
                    // for(int i=0; i<items.length;i++){
                    //     if(items[i].euqals(itemName)){
                    //         // System.out.println(itemName);
                    //         break;
                    //     }
                    // }
                }else{
                    easyGameOutputs.printCommandHelpErrorMessage("look");
                }
                break;
            case "view":
                if(commandParts[1].equals("map")){
                    System.out.println(gameMap.display(player));
                }else if(commandParts[1].equals("score")){
                    System.out.println("Your current score: " + player.getScore().getScore());
                }else if(commandParts[1].equals("inventory")){
                    System.out.println("Inventory of " + player.getName() + ": " + player.getInventory().displayInventory());
                }else{
                    easyGameOutputs.printCommandHelpErrorMessage("view");
                }
                break;
            case "move":
                if(commandParts.length == 2){
                    player.getPosition().move(commandParts[1], gameMap); //Use the new move method to dynamically move charater without the need for 4 methods (1 for each direction)
                }else{
                    // System.out.println("Invalid Move command. Please specify a direction");
                    easyGameOutputs.printCommandHelpErrorMessage("move");
                }
                break;
            case "enter":
                if(commandParts[1].equals("room")){

                }else{
                    easyGameOutputs.printCommandHelpErrorMessage("enter");
                }
                break;
            case "drop":
                if(commandParts[1].length() > 1){
                    if(player.getInventory().hasItem(commandParts[1]) > -1){
                        player.getInventory().removeItem(commandParts[1]);
                        System.out.println("You have dropped your " + commandParts[1]);
                    }else{
                        System.out.println("You do not have the item '" + commandParts[1] + "'");
                    }
                }else{
                    easyGameOutputs.printCommandHelpErrorMessage("drop");
                }
                break;
            case "quit":
                break;
            default:
                System.out.println("Unknown Command");
                break;
        }

    }
    
}