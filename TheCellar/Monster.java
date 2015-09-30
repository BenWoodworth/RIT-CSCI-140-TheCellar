/*
 * Monster.java
 * 
 * Version: 0
 * 
 * Revisions:
 *      Added constructor, move() and toString() methods.
 */

import java.util.List;

/**
 * The class that contains information and methods for the monster.
 * 
 * @author Ben Woodworth
 */
public class Monster {
    private Map map;
    private String name;

    /**
     * Constructor for the Monster.
     * 
     * @param map The map in which the monster is roaming.
     * @param name The monster's name.
     */
    public Monster(Map map, String name) {
        this.map = map;
        this.name = name;
    }

    /**
     * Move into another room. Attack the player if he's in the room.
     */
    public void move() {
        List<Hall> halls = Hall.getConnectingHalls(map.monsterRoom, map.halls);
        Hall[] hallsArr = halls.toArray(new Hall[0]);
        Hall hall = Hall.getRandomHall(map.monsterRoom, hallsArr);
        if (hall != null) {
            int newRoom = hall.getOtherRoom(map.monsterRoom);
            if (map.monsterRoom == map.curRoom) {
                map.gameValues.output(
                        name + " left the room through hall " + hall + "!");
            } else if (newRoom == map.curRoom) {
                map.gameValues.output(
                        name + " entered the room through hall " + hall + "!");
            }
            map.monsterRoom = newRoom;
        }
        if (map.monsterRoom == map.curRoom)
            map.player.fightMonster(this);
    }

    public String toString() {
        return name;
    }
}
