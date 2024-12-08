package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the look command, allowing the player to examine various elements of the game world.
 * 
 * <p>
 * The look command can provide details about the current room, its exits, features, or specific items and equipment.
 * Hidden objects are not included in the output unless explicitly revealed.
 * </p>
 */
public class Look extends Command {

    private String target;

    public Look(String target){
        this.commandType = CommandType.LOOK;
        this.target = target;
    }

    public String execute(GameState gameState){
        Room currentRoom = gameState.getMap().getCurrentRoom();
        Player player = gameState.getPlayer();
        StringBuilder out = new StringBuilder();

        //We attempt to locate the target string in case the user is trying to look at a specific GameObject, if it cannot find one then we return null
        GameObject targetGO = currentRoom.getAll().stream().filter(obj -> obj.getName().equals(this.target)).findFirst().orElse(null);

        if(this.target.equals("room")){
            //Get room description and descriptions of each visible object in room
            out.append(currentRoom.getDescription() + "\n");
            for(GameObject object : currentRoom.getAll()){ //Loop through room items
                if(object.getHidden() == false){ //If item is not set to be hidden
                    out.append("- " + object.getDescription() + "\n");
                }
            }
            return out.toString();
        }else if(this.target.equals("exits")){
            //Retrieve visible exits
            out.append("The available exits are:\n");
            for(Exit exit: currentRoom.getExits()){
                if(exit.getHidden() == false){ //If exit is not hidden, then include it in the outputted list
                    out.append("- " + exit.getDescription() + "\n");
                }
            }
        }else if(this.target.equals("features")) {
            out.append("You also see:\n");
            for (Feature feature : currentRoom.getFeatures()) {
                if (feature.getHidden() == false) { //If feature isn't hidden, then include it in the outputted list
                    out.append("- " + feature.getDescription() + "\n");
                }
            }
        }else if(targetGO != null){//If the above search managed to find the required target GameObject
            //Retrieve the GameObject by its name and then we can
            out.append(targetGO.getDescription());
        }else{
            System.out.println("Look.java - Line 60 needs to be completed");
        }
        return out.toString();
    }

    public String toString(){
        return "Look at target";
    }
   
}
