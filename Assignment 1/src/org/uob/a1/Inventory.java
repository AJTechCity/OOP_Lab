package org.uob.a1;

public class Inventory {

    final int MAX_ITEMS = 10;
    int current_items = 0;
    String[] items;

    public Inventory(){
        items = new String[MAX_ITEMS];
    }

    public void addItem(String item){
        if(this.current_items<MAX_ITEMS){
            items[current_items++] = item;
        }
    }

    public int hasItem(String item){
        int index=-1;

        for(int i=0;i<this.current_items;i++){
            if(this.items[i] == item){
                index=i;
            }
        }

        return index;
    }

    public void removeItem(String item){
        String[] new_items_array = new String[this.current_items-1];
        int new_items_count = 0;

        for(int i=0;i<this.current_items;i++){
            if(this.items[i] != item){
                new_items_array[new_items_count++] = item;
            }
        }

        this.items = new_items_array;
        this.current_items = new_items_count;
    }

    public String displayInventory(){
        String inventory="";
        for(int i=0;i<this.current_items;i++){
            inventory += this.items[i];
            if(i<this.current_items-1){
                inventory += " ";
            }
        }

        return inventory;
    }
}