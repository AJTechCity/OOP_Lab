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
        // TDO
        //Return string describing the result of using the equipment
        return "STRING";
    }

    @Override
    public String toString() {
        return super.toString() + ", useInformation=" + useInformation;
    }
}
