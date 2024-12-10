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
                   "- HELP\n" + this.helpHelp();
       }else if(this.topic == "move"){
           return "MOVE Command:\n" +
                   "Use the 'move' command to move your player around the map\n" +
                   "You must specify the direction which is north, south, east, or west" +
                   this.moveHelp();
       }else if(this.topic == "help"){
           return "HELP Command:\n" +
                   "Use the 'help' command to show this help message and others\n" +
                   this.helpHelp();
       }else if(this.topic == "another topic"){

       }else{
           return "No help available for the topic: " + this.topic;
       }
        return "Execute Help Command";
   }

   private String moveHelp(){
        return "move <north|south|east|west>";
   }

   private String helpHelp(){
        return "help <topic (optional)>";
   }

   public String toString(){
        return "{Command Type: " + this.commandType + "\n" +
                "topic: " + this.topic + "}";
   }
  
}
