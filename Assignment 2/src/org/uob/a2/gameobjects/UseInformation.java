package org.uob.a2.gameobjects;

/**
 * Represents information about how an object can be used in the game.
 * 
 * <p>
 * This class stores details about the usage of an object, such as whether it has
 * already been used, the type of action it performs, the target of the action,
 * the result of the action, and any associated message.
 * </p>
 */
public class UseInformation {

    private boolean isUsed;
    private String action; //What equipment does (open, reveal, combine)
    private String target; //Equipment to be used on this item (ItemName)
    private String result; //What item/feature is then shown (ItemName)
    private String message; //Message to be shown to user

    public UseInformation(boolean isUsed, String action, String target, String result, String message){
        this.isUsed = isUsed;
        this.action = action;
        this.target = target;
        this.result = result;
        this.message = message;
    }

    public boolean isUsed(){
        return this.isUsed;
    }

    public void setUsed(boolean isUsed){
        this.isUsed = isUsed;
    }

    public String getAction(){
        return this.action;
    }

    public void setAction(String action){
        this.action = action;
    }

    public String getTarget(){
        return this.target;
    }

    public void setTarget(String target){
        this.target = target;
    }

    public String getResult(){
        return this.result;
    }

    public void setResult(String result){
        this.result = result;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public String toYAML(){
        return this.action + "," + this.target + "," + this.result + "," + this.message + "," + (this.isUsed == false ? "false" : "true");
    }

    /**
     * Returns a string representation of the usage information, including all attributes.
     *
     * @return a string describing the usage information
     */
    @Override
    public String toString() {
        return "UseInformation{" +
                "isUsed=" + isUsed +
                ", action='" + action + '\'' +
                ", target='" + target + '\'' +
                ", result='" + result + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
