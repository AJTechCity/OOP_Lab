package org.uob.a1;

public class Player extends Character{
    private Inventory inventory;
    private Score score;
    private int damageMultiplier;

    public Player(String name, Position startPosition){
        super(name, startPosition, 100, 0, 10);
        this.inventory = new Inventory();
        this.inventory.addItem("health potion");
        this.inventory.addItem("sword");
        this.score = new Score(0);
        this.damageMultiplier = 1;
    }

    public Inventory getInventory(){
        return this.inventory;
    }

    public void attackCharacter(Character c){
        if(this.inventory.hasItem("sword")>-1){
            if(this.inventory.hasItem("double damage")>-1){
                //Use the damage multiplier for this current attack
                this.inventory.removeItem("double damage");
                damageMultiplier = 2;
            }else{
                //Make sure to reset damage multiplier to 1 in case it isn't reset
                this.damageMultiplier = 1;
            }
            System.out.println("System: You attacked " + c.getName() + "!");
            c.takeDamage(this, this.attackPower * damageMultiplier);
            this.damageMultiplier = 1; //Reset damage multiplier after attack
        }else{
            System.out.println("Error - You have no sword in your inventory");
        }
    }
}