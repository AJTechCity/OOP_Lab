package org.uob.a1;

class Enemy extends Character{

    private String description;
    private boolean penetratesProtection;

    public Enemy(String name, String description, int startHealth, int startProtection, int attackPower, Position position){
        super(name, position, startHealth, startProtection, attackPower);
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public boolean canPenetrateProtection(){
        return this.penetratesProtection;
    }

    public void setDecription(String description){
        this.description = description;
    }

    public void setCanPenetrateProtection(boolean p){
        this.penetratesProtection = p;
    }

    public void attackPlayer(Player p){
        System.out.println(this.name + " attacked " + p.getName() + "!");
        p.takeDamage(this, this.attackPower, !penetratesProtection);
    }
}