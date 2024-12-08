package org.uob.a2.gameobjects;

import java.util.ArrayList;

public class Score{

    private int visitedRooms;
    private int containersOpened;
    private int usedEquipmentInInventory;
    private int itemsInInventory
    private int startingScore;

    private ArrayList<Item> playerInventory;
    private ArrayList<Equipment> playerEquipment;


    public Score(int startingScore){
        this.startingScore = startingScore;
        this.visitedRooms = 0;
        this.containersOpened = 0;
        this.usedEquipmentInInventory = 0;
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

    private Int getNumItemsInInventory(){
        return playerInventory.size();
    }

    private Int getNumUsedEquipmentInInventory(){
        return playerEquipment.stream().filter(obj->obj.getUseInformation().isUsed() == true).size();
    }

    public void updateScoreVariables(){
        this.usedEquipmentInInventory = getNumUsedEquipmentInInventory();
        this.itemsInInventory = getNumItemsInInventory();
    }

    public Int getScore(){
        this.updateScoreVariables();
        int returnScore=0;

        //Score = startScore + 2 * (containersOpened + itemsInInventory - (2 * usedEquipmentInInventory) - visitedRooms)
        returnScore += containersOpened + itemsInInventory - (2 * usedEquipmentInInventory) - visitedRooms;
        returnScore *= 2;
        returnScore += startingScore;

        return returnScore;
    }
}