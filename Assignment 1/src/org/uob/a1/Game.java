package org.uob.a1;

import java.util.Scanner; 

public class Game {

    private static Map gameMap = new Map(20,10);
    private static EasyGameOutputs easyGameOutputs = new EasyGameOutputs();;
    private static Scanner scanner = new Scanner(System.in);
    private static String command = "";
    private static Score playerScore = new Score(0);
    private static Player player;

    //ROOMS

    //ENEMIES

    private static Enemy weaklingBandit = new Enemy("Weakling Bandit", "Beware the weakling bandit, a grandson of the Brutal Ogre. He serves as a knight to the Ogre and defends itself with an attack. He has 40 health, no protection, and a broken wooden sword. A weak enemy. Defeating him provides you with a protection potion to increase your protection", 40, 0, 7, new Position(0,4));
    private static Enemy theMite = new Enemy("The Mite", "Beware The Mite, a grandson of the Brutal Ogre. He mystically mutated and gained protection spells and a metal sword. He has 35 health, 20 protection, and will attack when provoked. Upon defeating him, his protection spell oozes out and becoms available to you", 35, 20, 15, new Position(17,1));
    private static Enemy e3 = new Enemy("Enemy 3", "dirty enemy 3", 10, 0, 10, new Position(11,7));
    //PUZZLES
    
    public static void main(String[] args) {
        welcomeScreen(); //Runs the welcome screen things and won't advance until user presses enter
        userInfoSetup();
        gameInit();

        do{
            System.out.print("> ");
            command = scanner.nextLine();
            parseCommand(command);

            // player.attackCharacter(weaklingBandit);
            // player.attackCharacter(theMite);
            // player.setHealth(10);
            // player.setProtection(0);
            // theMite.attackPlayer(player);


            // command = "quit";

        }while(!command.equals("quit"));

        System.out.println("Game over - user quit the experience");
        System.out.println("Final score was: " + playerScore.getScore());

        System.out.println(gameMap.display(player));
    }

    private static boolean welcomeScreen(){
        easyGameOutputs.printTitle("      THE MAP      ");
        System.out.println("Welcome to 'THE MAP' - a thrilling text-based adventure game.");
        System.out.print("Press Enter to continue to user information setup...");
        scanner.nextLine();
        return true;
    }

    private static boolean userInfoSetup(){
        easyGameOutputs.printTitle("USER INFORMATION SETUP");
        System.out.print("Please enter your name: ");
        String nameInput = scanner.nextLine();
        player = new Player(nameInput, new Position(0,0));

        System.out.println("Welcome to the game " + player.getName());
        System.out.println("Type 'help' at any time to view the list of available commands");
        System.out.println("Type 'quit' at any time to exit the game");

        return true;

    }

    private static void gameInit(){
        Room[] rooms = { //Create array with all rooms within it
            new Room("Room A", "A dusty, dirty old room", 'A', new Position(1,2)),
            new Room("Room B", "A dusty, dirty old room", 'B', new Position(4,1)),
            new Room("Room C", "A dusty, dirty old room", 'C', new Position(1,3)),
            new Room("Room D", "A dusty, dirty old room", 'D', new Position(4,5)),
            new Room("Room E", "A dusty, dirty old room", 'E', new Position(2,6)),
        };

        Enemy[] enemies = { //create array with enemies in it
            weaklingBandit,
            theMite,
            e3
        };

        for(int i=0;i<rooms.length;i++){ //Loop over rooms array so that they can be added to map programatically
            Room r = rooms[i];
            gameMap.placeRoom(r.getPosition(), r.getSymbol());
        }

        for(int i=0;i<enemies.length;i++){ //Loop over rooms array so that they can be added to map programatically
            Enemy e = enemies[i];
            gameMap.placeCharacter(e, 'e');
        }
    }

    public static void parseCommand(String c){
        String[] commandParts = command.split(" ");
        String action = commandParts[0];

        if(action.equals("help")){
            easyGameOutputs.printCommandHelp();
            return;
        }else if(commandParts.length != 2){
            System.out.println("Invalid Command");
            return;
        }
        switch(action.toLowerCase()){
            case "view":
                if(commandParts[1].equals("map")){
                    System.out.println(gameMap.display(player));
                }else{
                    System.out.println("Invalid View command. Type 'help' for available commands");
                }
                break;
            case "move":
                if(commandParts[1].equals("north")){
                    player.getPosition().moveNorth(gameMap);
                }else if(commandParts[1].equals("south")){
                    player.getPosition().moveSouth(gameMap);
                }else if(commandParts[1].equals("east")){
                    player.getPosition().moveEast(gameMap);
                }else if(commandParts[1].equals("west")){
                    player.getPosition().moveWest(gameMap);
                }else{
                    System.out.println("Invalid Move command. Type 'help' for available commands");
                }
                break;
            case "drop":
                if(commandParts[1].length() > 1){
                    if(player.getInventory().hasItem(commandParts[1]) > -1){
                        player.getInventory().removeItem(commandParts[1]);
                    }else{
                        System.out.println("You do not have the itme '" + commandParts[1] + "'");
                    }
                }else{
                    System.out.println("Please specify an item to drop");
                }
                break;
            case "quit":
                System.out.println("quit Command");
                break;
            default:
                System.out.println("Unknown Command");
                break;
        }

    }
    
}