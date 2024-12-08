package org.uob.a2.gameobjects;

public class Equipment extends GameObject implements Usable {
   
    /**
     * Returns a string representation of this equipment, including the attributes inherited from {@code GameObject}
     * and the associated use information.
     *
     * @return a string describing the equipment
     */

    protected UseInformation useInformation;

    public Equipment(String id, String name, String description, boolean hidden, UseInformation useInformation){
        super(id, name, description, hidden);
        this.useInformation = useInformation;
    }

    public void setUseInformation(UseInformation useInformation){
        this.useInformation = useInformation;
    }

    public UseInformation getUseInformation() {
        return useInformation;
    }

    public String use(GameObject target, GameState gameState){
        // Only called if the target is a valid object
        //Return string describing the result of using the equipment
        Room currentRoom = gameState.getMap().getCurrentRoom();
        String revealItemId = this.useInformation.getResult();
        GameObject itemToReveal = currentRoom.getAll().stream().filter(obj->obj.getId().equals(revealItemId)).findFirst().orElse(null);
        if(itemToReveal == null){
            return "Result item doesn't exist";
        }

        if(itemToReveal.getHidden() == false){
            return "Result Already Visible";
        }

        if(this.useInformation.getAction().equals("reveal")){ //If the equipment reveals hidden Objects in the room
            if(!currentRoom.getId().equals(useInformation.getTarget())) return "You can only use this equipment in room " + useInformation.getTarget();
        }

        //Same Default logic to reveal item and set equipment as used
        itemToReveal.setHidden(false);
        this.useInformation.setUsed(true);

        return this.useInformation.getMessage();
    }

    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
