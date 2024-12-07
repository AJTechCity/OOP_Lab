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
        return "Execute Help Command";
   }

   public String toString(){
        return "{Command Type: " + this.commandType + "\n" +
                "topic: " + this.topic + "}";
   }
  
}
