package org.uob.a2.gameobjects;

import java.util.ArrayList;

/**
 * Represents the current state of the game, including the map and the player.
 * 
 * <p>
 * The game state contains all necessary information about the game's progress, such as
 * the player's status and the state of the game map.
 * </p>
 */
public class GameState {

    private Map map;
    private Player player;
    private Score playerScore;
    private ArrayList<Combination> combinations;

    public String savedFilename; //If the user already has data stored, we can reuse the old file to store the data again

    public GameState(Map map, Player player){
        this.map = map;
        this.player = player;
        this.savedFilename = null;
        this.combinations = new ArrayList<Combination>();
    }

    public GameState(){}

    public Map getMap(){
        return this.map;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void setPlayerScore(Score score){
        this.playerScore = score;
    }

    public Score getPlayerScore(){
        return this.playerScore;
    }

    public ArrayList<Combination> getCombinations(){
        return this.combinations;
    }

    public void addCombination(Combination combination){
        this.combinations.add(combination);
    }

    public Combination findCombination(Item item1, Item item2){
        for(Combination combination: this.combinations){
            if(combination.isValidCombinationItems(item1, item2)){
                return combination;
            }
        }
        return null;
    }

    /**
     * Returns a string representation of the game state, including the map and player details.
     *
     * @return a string describing the game state
     */
    @Override
    public String toString() {
        return "GameState {" +
               "map=" + (map != null ? map.toString() : "null") + ", " +
               "player=" + (player != null ? player.toString() : "null") +
               '}';
    }
}
