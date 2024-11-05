package org.uob.a1;

import java.util.Scanner; 

public class Game {

    private static Map gameMap = new Map(10,20);
    private static EasyGameOutputs easyGameOutputs = new EasyGameOutputs();;
    private static Scanner scanner = new Scanner(System.in);
    private static String command = "";
    private static Player player = new Player("", new Position(0,0));

    //PUZZLES
    private static Puzzle codePuzzle1 = new CodePuzzle("prize", player, "987851");
    private static Puzzle codePuzzle2 = new CodePuzzle("prize", player, "651227");
    private static Puzzle codePuzzle3 = new CodePuzzle("prize", player, "123456");
    private static Puzzle codePuzzle4 = new CodePuzzle("prize", player, "325648");


    //ROOMS
    private static Room RoomA = new Room("First Step to Success Room", "A dusty, dirty old room", 'A', new Position(2,1), codePuzzle1);
    private static Room RoomB = new Room("Room B", "A dusty, dirty old room", 'B', new Position(0,3));
    private static Room RoomC = new Room("Room C", "A dusty, dirty old room", 'C', new Position(2,4), codePuzzle2);
    private static Room RoomD = new Room("Room D", "A dusty, dirty old room", 'D', new Position(2,6), codePuzzle1);
    private static Room RoomE = new Room("Room E", "A dusty, dirty old room", 'E', new Position(0,6), codePuzzle1);
    private static Room RoomF = new Room("Room F", "A dusty, dirty old room", 'F', new Position(4,1), codePuzzle3);
    private static Room RoomG = new Room("Room G", "A dusty, dirty old room", 'G', new Position(3,8), codePuzzle1);
    private static Room RoomH = new Room("Room H", "A dusty, dirty old room", 'H', new Position(6,3), codePuzzle1);
    private static Room RoomI = new Room("Room I", "A dusty, dirty old room", 'I', new Position(5,7), codePuzzle1);
    private static Room RoomJ = new Room("Room J", "A dusty, dirty old room", 'J', new Position(3,13), codePuzzle4);
    private static Room RoomK = new Room("Room K", "A dusty, dirty old room", 'K', new Position(8,17), codePuzzle1);
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
                try{
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
                }catch (ArrayIndexOutOfBoundsException e){
                    System.out.println("Invalid Room Symbol");
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