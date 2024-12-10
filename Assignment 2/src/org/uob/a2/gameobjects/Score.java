package org.uob.a2.gameobjects;

import java.util.ArrayList;

public class Score{

    private int visitedRooms;
    private int containersOpened;
    private int usedEquipmentInInventory;
    private int unUsedEquipmentInInventory;
    private int itemsInInventory;
    private int startingScore;

    private ArrayList<Item> playerInventory;
    private ArrayList<Equipment> playerEquipment;


    public Score(int startingScore){
        this.startingScore = startingScore;
        this.visitedRooms = 0;
        this.containersOpened = 0;
        this.usedEquipmentInInventory = 0;
        this.unUsedEquipmentInInventory = 0;
    }

    public void setPlayerInventory(ArrayList<Item> pInv){
        this.playerInventory = pInv;
    }

    public void setPlayerEquipment(ArrayList<Equipment> pEqu){
        this.playerEquipment = pEqu;
    }

    public void visitRoom(){
        this.visitedRooms++;
    }

    private int getNumItemsInInventory(){
        return playerInventory.size();
    }

    private int getNumUsedEquipmentInInventory(){
        return (int) playerEquipment.stream().filter(obj->obj.getUseInformation().isUsed() == true).count();
    }

    private int getNumUnusedEquipmentInInventory(){
        return (int) playerEquipment.stream().filter(obj->obj.getUseInformation().isUsed() == false).count();
    }

    public void updateScoreVariables(){
        this.usedEquipmentInInventory = getNumUsedEquipmentInInventory();
        this.itemsInInventory = getNumItemsInInventory();
        this.unUsedEquipmentInInventory = getNumUnusedEquipmentInInventory();
    }

    public int getScore(){
        this.updateScoreVariables();
        int returnScore=0;

        returnScore += containersOpened + itemsInInventory + unUsedEquipmentInInventory;
        returnScore *= 5;
        returnScore += startingScore - visitedRooms - usedEquipmentInInventory;;

        return returnScore;
    }

    public String toYAML(){
        this.updateScoreVariables();
        return Integer.toString(this.getScore());
    }
}