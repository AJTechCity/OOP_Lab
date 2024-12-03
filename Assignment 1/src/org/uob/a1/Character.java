package org.uob.a1;

public class Character{

    //A Base class made for the Player and Enemy Objects
    
    /*
        Provides the basic variables and methods that are shared between
        the two object types. This is not currently fully used within the
        game logic but can be expanded so the user can intereact with NPC's
        and 'attack' them for points and prizes
    */

    final int MAX_HEALTH = 100;
    final int MAX_PROTECTION = 100;

    protected String name;
    protected Position position;
    protected int health;
    protected int protection;
    protected int attackPower;
    private boolean isDead;

    public Character(String name, Position position, int startHealth, int startProtection, int attackPower){
        this.name = name;
        this.position = position;
        this.health = startHealth;
        this.protection = startProtection;
        this.attackPower = attackPower;
        this.isDead = false;
    }

    public String getName(){
        return this.name;
    }

    public Position getPosition(){
        return this.position;
    }

    public int getHealth(){
        return this.health;
    }

    public int getProtection(){
        return this.protection;
    }

    public int getAttackPower(){
        return this.attackPower;
    }

    public boolean characterIsDead(){
        return this.isDead;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPosition(Position p){
        this.position = p;
    }

    public void addHealth(int amount){
        this.health += amount;
        if(this.health > MAX_HEALTH){
            //If current health is above max, then add to protection and set health to max
            this.addProtection(this.health - MAX_HEALTH);
            this.health = MAX_HEALTH;
        }
    }

    public void addProtection(int amount){
        this.protection += amount;
        if(this.protection > MAX_PROTECTION){
            //if current protection is above max, then set to max protection
            this.protection = MAX_PROTECTION;
        }
    }

    public void setHealth(int health){
        this.health = health;
    }

    public void setProtection(int protection){
        this.protection = protection;
    }

    public void setIsDead(boolean dead){
        this.isDead = dead;
    }

    public void takeDamage(Character attacker, int damage){
        //Default function for character to take damage using protection in case the parameter isn't passed
        this.takeDamage(attacker, damage, true);
    }

    public void takeDamage(Character attacker, int damage, boolean useProtection){
        if(useProtection){
            this.protection -= damage; //take the protection;
            if(this.protection < 0){
                // if protection becomes negative then set to 0 and take user's health
                this.health += this.protection;
                this.protection = 0;
            }
        }else{
            this.health -= damage;
        }

        if(this.protection <= 0 && this.health <= 0){
            this.isDead = true;
            System.out.println(this.name + " has been killed! " + attacker.name + " has gained 10 points.");
        }
    }
}