/*
 * GameValues.java
 * 
 * Version: 2
 * 
 * Revisions:
 *      Added public variables that the view might need.
 *      Added accessors and mutators. Made vars private. Made class observable.
 *      Added changed() method. Made mutators call changed() method.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Values in the game that the window will observe
 * 
 * @author Ben Woodworth
 */
public class GameValues extends Observable {
    private List<String> playerItems, roomItems;
    private int playerExp, playerHealth;
    private List<Hall> halls;
    private String newOutput;
    private boolean gameOver;
    
    /**
     * Constructs a new GameValues object.
     */
    public GameValues() {
        playerItems = new ArrayList<String>();
        roomItems = new ArrayList<String>();
        playerExp = 1;
        playerHealth = 100;
        halls = new ArrayList<Hall>();
        newOutput = "";
        gameOver = false;
    }
    
    /**
     * Notifies observers that values have been changed.
     */
    private void changed() {
        setChanged();
        notifyObservers();
    }
    
    /**
     * Append a string to the game output.
     * @param s The string to append
     */
    public void output(String s) {
        StringBuilder sb = new StringBuilder(newOutput);
        sb.append(s);
        sb.append("\n\n");
        newOutput = sb.toString();
        changed();
    }
    
    /**
     * Append a horizontal rule made of equal signs.
     */
    public void outputSeparator() {
        output("====================================================");
    }
    
    /**
     * Get the output to append.
     * @return The output to append.
     */
    public String getNewOutput() {
        String result = newOutput;
        newOutput = "";
        return result;
    }
    
    /**
     * Update the items that the player is currently holding.
     * @param items The items the player is holding.
     */
    public void setPlayerItems(List<String> items) {
        playerItems = items;
        changed();
    }
    
    /**
     * Get the items that the player is holding.
     * @return The items that the player is holding.
     */
    public List<String> getPlayerItems() {
        return playerItems;
    }
    
    /**
     * Update the items that are in the current room.
     * @param items The items that are in the current room.
     */
    public void setRoomItems(List<String> items) {
        roomItems = items;
        changed();
    }
    
    /**
     * Get the items that are in the current room.
     * @return The items that are in the current room.
     */
    public List<String> getRoomItems() {
        return roomItems;
    }
    
    /**
     * Update the player's current experience level.
     * @param exp The player's current experience level.
     */
    public void setPlayerExp(int exp) {
        playerExp = exp;
        changed();
    }
    
    /**
     * Get the player's current experience level.
     * @return The player's current experience level.
     */
    public int getPlayerExp() {
        return playerExp;
    }
    
    /**
     * Update the player's current health.
     * @param health The player's current health.
     */
    public void setPlayerHealth(int health) {
        playerHealth = health;
        changed();
    }
    
    /**
     * Get the player's current health.
     * @return The player's current health.
     */
    public int getPlayerHealth() {
        return playerHealth;
    }
    
    /**
     * Update the list of halls connected to the current room.
     * @param halls The list of halls connected to the current room.
     */
    public void setHalls(List<Hall> halls) {
        this.halls = halls;
        changed();
    }
    
    /**
     * Get the list of halls connected to the current room.
     * @return The list of halls connected to the current room.
     */
    public List<Hall> getHalls() {
        return halls;
    }
    
    /**
     * End the game.
     */
    public void gameOver() {
        gameOver = true;
        changed();
    }
    
    /**
     * Check if the game has ended.
     * 
     * @return Returns true if the game has ended.
     */
    public boolean isGameOver() {
        return gameOver;
    }
}

