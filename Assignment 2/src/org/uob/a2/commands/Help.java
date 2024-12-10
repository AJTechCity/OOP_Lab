package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the help command, providing the player with instructions or information
 * about various topics related to the game.
 * 
 * <p>
 * The help command displays information on how to play the game, including details about 
 * available commands, their syntax, and their purpose.
 * </p>
 */
public class Help extends Command {

    private String topic;

    public Help(String topic){
        this.commandType = CommandType.HELP;
        this.topic = topic;
    }

   public String execute(GameState gameState){
        //TO DO
       if(this.topic == null){
           return "Welcome to the game!\n"+
                   "- MOVE\n" + this.moveHelp() + "\n" +
                   "- LOOK\n" + this.lookHelp() + "\n" +
                   "- GET\n" + this.getHelp() + "\n" +
                   "- DROP\n" + this.dropHelp() + "\n" +
                   "- USE\n" + this.useHelp() + "\n" +
                   "- STATUS\n" + this.statusHelp() + "\n" +
                   "- HELP\n" + this.helpHelp() + "\n" +
                   "- COMBINE\n" + this.combineHelp() + "\n" +
                   "- QUIT\n" + this.quitHelp() + "\n" +
                   "";
       }else if(this.topic.equals("move")){
           return "MOVE Command:\n" +
                   "Use the 'move' command to move your player around the map\n" +
                   "You must specify the direction which is north, south, east, or west" +
                   this.moveHelp();
       }else if(this.topic.equals("help")){
           return "HELP Command:\n" +
                   "Use the 'help' command to show this help message and others\n" +
                   this.helpHelp();
       }else if(this.topic.equals("look") {
           return "LOOK Command:\n" +
                   "Use the 'look' command to look around the current room, an exit, a feature, a specific item, equipment, or feature\n" +
                   this.lookHelp();
       }else if(this.topic.equals("get")){
           return "GET Command:\n" +
                   "Use the 'get' command to pick up an item or equipment from the current room (e.g. 'get key'\n" +
                   this.getHelp();
       }else if(this.topic.equals("use")) {
           return "USE Command:\n" +
                   "Use the 'use' command to use an tem in your inventory on its own or on a feature (e.g. use key on chest)\n" +
                   this.useHelp();
       }else if(this.topic.equals("drop")) {
           return "DROP Command:\n" +
                   "Use the 'drop' command to drop an item or equipment from your inventory (e.g. drop key)\n" +
                   this.dropHelp();
       }else if(this.topic.equals("status")) {
           return "STATUS Command:\n" +
                   "Use the 'status' command to check your current status, inventory, or get more information about equipment in your inventory (e.g. 'status player', 'status score'\n" +
                   this.statusHelp();
       }else if(this.topic.equals("combine")) {
           return "COMBINE Command:\n" +
                   "Use the 'combine' command to combine two items into a new equipment piece (e.g. combine metal with wood)\n" +
                   this,getCombineHelp();
       }else if(this.topic.equals("quit")){
           return "QUIT Command:\n" +
                   "Use the 'quit' command to save and quit the game\n" +
                   this.getQuitHelp();
       }else{
           return "No help available for the topic: " + this.topic;
       }
        return "Execute Help Command";
   }

   private String moveHelp(){
        return "move <north|south|east|west>";
   }

   private String lookHelp(){
        return "look <room|exits|features> | <item name> | <equipment name> | <feature name>"
   }

   private String getHelp(){
        return "get <item name | equipment name>";
   }

   private String dropHelp(){
        return "drop <item name | equipment name>";
   }

   private String useHelp(){
        return "use <equipment name> on <feature>";
   }

   private String statusHelp(){
        return "status <inventory|player|item name|equipment name|score>";
   }

   private String helpHelp(){return "help <topic (optional)>";}

   private String combineHelp(){
        return "combine <item name> with <item name>";
   }

    private String quitHelp(){return "quit";}

   public String toString(){
        return "{Command Type: " + this.commandType + "\n" +
                "topic: " + this.topic + "}";
   }
  
}
