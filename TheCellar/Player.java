/*
 * Player.java
 * 
 * Version: 4
 * 
 * Revisions:
 *      Added constructor
 *      Added pickUpItem(), dropItem() methods.
 *      Added enterHall() and warp() methods.
 *      Added methods coding easier.
 *      Added a fightMonster() method.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The class that contains information and methods for the player.
 * 
 * @author Ben Woodworth
 */
public class Player {
    private Map map;

    /**
     * Constructor for the player.
     * 
     * @param map The map the player is in.
     * @param health The player's starting Life Force.
     * @param exp The player's starting Experience
     */
    public Player(Map map, int health, int exp) {
        this.map = map;
        map.gameValues.setPlayerHealth(health);
        map.gameValues.setPlayerExp(exp);
        map.gameValues.setPlayerItems(new ArrayList<String>());
    }

    /**
     * Pick up an item that's in the room.
     * 
     * @param item The item to pick up.
     */
    public void pickUpItem(String item) {
        map.gameValues.outputSeparator();
        int protectionsCount = Items.getProtections(getItems()).size();
        if (protectionsCount < getExp() || !Items.isProtection(item)) {
            if (map.getCurrentRoom().getItems().remove(item)) {
                getItems().add(item);
                if (Items.isAmulet(item)) {
                    output("You found the amulet. you win!");
                    map.gameValues.gameOver();
                } else if (Items.isSword(item)) {
                    output("You picked up a Sword");
                } else {
                    output("You picked up an item: " + item);
                }
                if (map.monster != null) map.monster.move();
            } else {
                output("Unable to pick up item: " + item);
            }
        } else {
            output("You don't have enough experience to pick up this item!");
        }
        map.gameValues.notifyObservers();
    }

    /**
     * Drop an item that's in the player's inventory.
     * @param item The item to drop.
     */
    public void dropItem(String item) {
        map.gameValues.outputSeparator();
        if (getItems().remove(item)) {
            map.getCurrentRoom().getItems().add(item);
            output("You dropped an item: " + item);
            if (map.monster != null) map.monster.move();
        } else {
            output("Unable to drop item: " + item);
        }
        map.gameValues.notifyObservers();
    }

    /**
     * Enter a hallway connected to the room.
     * 
     * @param hall The hallway to enter
     */
    public void enterHall(Hall hall) {
        map.gameValues.outputSeparator();
        output("You entered another room through hall " + hall);
        warp(hall.getOtherRoom(map.curRoom));
        if (map.monsterRoom == map.curRoom && map.monster != null) {
            output("\nYou've encountered " + map.monster + "!");
            fightMonster(map.monster);
        }
        if (map.monster != null)
            map.monster.move();
        map.gameValues.notifyObservers();
    }

    /**
     * Move the player to another room.
     * 
     * @param room The room to move the player to.
     */
    public void warp(int room) {
        map.curRoom = room;
        map.gameValues.setRoomItems(map.getCurrentRoom().getItems());
        map.gameValues.setHalls(Hall.getConnectingHalls(room, map.halls));
        Trap trap = map.getCurrentRoom().getTrap();
        if (trap != null) {
            trap.apply(this);
        }
    }

    /**
     * Get the items in the player's inventory.
     * 
     * @return Return's the items in the player's inventory.
     */
    public List<String> getItems() {
        return map.gameValues.getPlayerItems();
    }

    /**
     * Get the player's current Life Force.
     * 
     * @return Return's the player's current Life Force.
     */
    public int getHealth() {
        return map.gameValues.getPlayerHealth();
    }

    /**
     * Get the player's current Experience Level.
     * 
     * @return Return's the player's current Experience Level.
     */
    public int getExp() {
        return map.gameValues.getPlayerExp();
    }

    public void setHealth(int health) {
        if (health < 0) health = 0;
        map.gameValues.setPlayerHealth(health);
        if (health == 0) {
            output("You have been slain by the monster. You lose!");
            map.gameValues.gameOver();
        }
    }

    /**
     * Check if the player has a certain item.
     * 
     * @param item The item to check whether the player has.
     * @return Returns true if the player has the item.
     */
    public boolean hasItem(String item) {
        return Items.hasItem(getItems(), item);
    }

    /**
     * Remove an item from the player's inventory.
     * 
     * @param item The item to remove from the player's inventory.
     * @return Returns true if the player had the item.
     */
    public boolean removeItem(String item) {
        return getItems().remove(item);
    }

    /**
     * Output a message to the game output.
     * 
     * @param s The string to output.
     */
    public void output(String s) {
        map.gameValues.output(s);
    }

    /**
     * Gets the map the player's in.
     * 
     * @return Returns the map the player is in.
     */
    public Map getMap() {
        return map;
    }

    /**
     * Fight a monster.
     * 
     * @param monster The monster to fight.
     */
    public void fightMonster(Monster monster) {
        if (Items.hasSword(getItems())) {
            output("You've slain " + map.monster + "!");
            map.monster = null;
        } else {
            int damage = Math.max(getHealth() / 2, 10);
            setHealth(getHealth() - damage);
            output(monster + " attacked you!\n" +                   
                    "You took " + damage + " damage");
        }
    }
}
