package org.uob.a1;

public class Inventory {

    final int MAX_ITEMS = 10;
    private int currentItems = 0;
    public String[] items;

    public Inventory(){
        items = new String[MAX_ITEMS];
    }

    public void addItem(String item){
        if(this.currentItems<MAX_ITEMS){
            items[currentItems++] = item;
        }
    }

    public int hasItem(String item){
        //returns index of an item if the user has it, -1 otherwise
        int index=-1;

        for(int i=0;i<this.currentItems;i++){
            if(this.items[i].equals(item)){
                index=i;
                break;
            }
        }

        return index;
    }

    public void removeItem(String item){
        //removes an item if 

        String[] newItemsArray = new String[this.currentItems-1];
        int newItemsCount = 0;

        for(int i=0;i<this.currentItems;i++){
            if(!this.items[i].equals(item)){
                newItemsArray[newItemsCount] = this.items[i];
                newItemsCount++;
            }
        }

        this.items = newItemsArray;
        this.currentItems = newItemsCount;
    }

    public String displayInventory(){
        String inventory="";
        for(int i=0;i<this.currentItems;i++){
            inventory += this.items[i] + " ";
        }

        return inventory;
    }
}