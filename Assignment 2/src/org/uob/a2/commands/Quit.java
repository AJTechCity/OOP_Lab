package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the quit command, allowing the player to exit the game.
 * 
 * <p>
 * The quit command signals the end of the game session and provides a summary of the player's
 * current status before termination.
 * </p>
 */
public class Quit extends Command {

    public Quit(){
        this.commandType = CommandType.QUIT;
    }

    public String execute(GameState gameState){
        Player player = gameState.getPlayer();
        return "Game over:\n" +
                player;
    }
 
}
