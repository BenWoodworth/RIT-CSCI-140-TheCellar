/*
 * Room.java
 * 
 * Version: 0
 * 
 * Revisions:
 *      Wrote constructor and accessors
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the rooms in the maze.
 * 
 * @author Ben Woodworth
 */
public class Room {
    private Trap trap;
    private List<String> items;
    
    /**
     * Constructor for the Room class.
     * 
     * @param trap The trap in the room.
     * @param items The items in the room.
     */
    public Room(Trap trap, String... items) {
        this.trap = trap;
        this.items = new ArrayList<String>();
        for (String item : items)
            this.items.add(item);
    }
    
    /**
     * Get the items currently in the room.
     * 
     * @return Returns the items currently in the room.
     */
    public List<String> getItems() {
        return items;
    }
    
    /**
     * Get the trap in the room.
     * 
     * @return Returns the trap in the room.
     */
    public Trap getTrap() {
        return trap;
    }
}
