/*
 * Hall.java
 * 
 * Version: 1
 * 
 * Revisions:
 *      Added variables and useful methods
 *      Added getRandomHall() method for use by the Monster class
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A hallway to connect between two rooms.
 * @author Ben Woodworth
 *
 */
public class Hall {
    String name;
    int roomA;
    int roomB;
    
    /**
     * A constructor for the hall class
     * 
     * @param name The name of the hallway.
     * @param roomA One of the rooms the hallway connects to.
     * @param roomB The other room the hallway connects to.
     */
    public Hall(String name, int roomA, int roomB) {
        this.name = name;
        this.roomA = roomA;
        this.roomB = roomB;
    }
    
    /**
     * Hall constructor with String room indices for lazy programming
     * 
     * @param name The name of the hallway.
     * @param roomA One of the rooms the hallway connects to.
     * @param roomB The other room the hallway connects to.
     */
    public Hall(String name, String roomA, String roomB) {
        this(name, Integer.parseInt(roomA), Integer.parseInt(roomB));
    }
    
    public String toString() {
        return name;
    }
    
    /**
     * Determines if the hallway is connected to the given room.
     * 
     * @param room The room to test.
     * @return Returns true if this hallway is connected to the given room.
     */
    public boolean connectsToRoom(int room) {
        return roomA == room || roomB == room;
    }
    
    /**
     * Returns the other room given one of the rooms connecting to this hallway.
     * 
     * @param room The room to check.
     * @return Returns the other room the hallway is connecting to.
     */
    public int getOtherRoom(int room) {
        return roomA == room ? roomB : roomA;
    }
    
    /**
     * Get all the hallways connected to a room.
     * 
     * @param room The room to get the connecting hallways to.
     * @param halls The array of hallways to check.
     * @return Returns a list of hallways connecting to the given room.
     */
    public static List<Hall> getConnectingHalls(int room, Hall[] halls) {
        List<Hall> result = new ArrayList<Hall>();
        for (Hall hall : halls)
            if (hall.connectsToRoom(room))
                result.add(hall);
        return result;
    }
    
    /**
     * Get a random hallway connected to a room.
     * 
     * @param room The room to get the connecting hallways to
     * @param halls halls The array of hallways to check.
     * @return Returns a random hallway connected to the room.
     */
    public static Hall getRandomHall(int room, Hall[] halls) {
        List<Hall> connected = getConnectingHalls(room, halls);
        if (connected.size() == 0) return null;
        int test = (int)(Math.random() * connected.size());
        return connected.get(test);
    }
}
