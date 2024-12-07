package org.uob.a2.commands;

import org.uob.a2.gameobjects.*;

/**
 * Represents the use command, allowing the player to use equipment on a specific target in the game.
 * 
 * <p>
 * The use command checks if the player has the specified equipment and whether it can interact with
 * the target. The target can be a feature, item, or the current room, depending on the game context.
 * </p>
 */
public class Use extends Command {

    private String equipmentName;
    private String target;

    public Use(String equipmentName, String target){
        this.commandType = CommandType.USE;
        this.equipmentName = equipmentName;
        this.target = target;
    }

    @Override
    public String toString(){
        return "TOSTRING";
    }

    @Override
    public String execute(GameState gameState){
        //Check if player has the equipment
        //Check if this equipmet can be used with the target
        //If valid, Use the equipment on the target (set as used)
        //Return result string
        //Otherwise, return an error string
        Player player = gameState.getPlayer();
        Equipment equipment = player.getEquipment(equipmentName);
        Container targetObj = (Container) gameState.getMap().getCurrentRoom().getFeatureByName(this.target);
        if(equipment != null){
            UseInformation equipmentUseInfo = equipment.getUseInformation();

            if(equipmentUseInfo.isUsed() == true){ //Return error if already used equipment
                return "You have already used " + equipmentName;
            }

            if(equipmentUseInfo.getTarget().equals(targetObj.getId())){
                //Get String target as a game object
                String equipmentAction = equipmentUseInfo.getAction();
                return equipment.use(targetObj, gameState); //Uses equipment and sets equipment as used
            }else{
                return "Invalid use target: " + this.equipmentName + " cannot be used on " + this.target;
            }
        }else{
            return "You do not have " + this.equipmentName;
        }
    }
  
}
