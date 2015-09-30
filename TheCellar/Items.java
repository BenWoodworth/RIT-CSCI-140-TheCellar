/*
 * Items.java
 * 
 * Version: 0
 * 
 * Revisions:
 *      Added constants and useful methods
 */

import java.util.ArrayList;
import java.util.List;

/**
 * A class with useful item-related methods.
 * 
 * @author Ben Woodworth
 */
public class Items {
    public static String AMULET = "Amulet";
    public static String SWORD = "Sword";
    
    /**
     * Check if an item is a protection.
     * 
     * @param item Item to check.
     * @return Returns true if the given item is a protection.
     */
    public static boolean isProtection(String item) {
        return !(isAmulet(item) || isSword(item));
    }
    
    /**
     * Check if an item is the Amulet.
     * 
     * @param item The item to check.
     * @return Returns true if the given item is the Amulet.
     */
    public static boolean isAmulet(String item) {
        return item.equalsIgnoreCase(AMULET);
    }
    
    /**
     * Check if an item is the Sword.
     * 
     * @param item The item to check.
     * @return Returns true if the given item is the Sword.
     */
    public static boolean isSword(String item) {
        return item.equalsIgnoreCase(SWORD);
    }
    
    /**
     * Get all the protections from a list of items.
     * 
     * @param items The list of items.
     * @return Returns all the protections in the given list of items.
     */
    public static List<String> getProtections(List<String> items) {
        List<String> result = new ArrayList<String>();
        for (String s : items) if (isProtection(s)) result.add(s);
        return result;
    }
    
    /**
     * Check if the list of items contains an item.
     * 
     * @param items The list of items to check.
     * @param item The item to check for.
     * @return Returns true if the given list of items contains the given item.
     */
    public static boolean hasItem(List<String> items, String item) {
        for (String s : items)
            if (s.equalsIgnoreCase(item))
                return true;
        return false;
    }
    
    /**
     * Checks if a list of items contains the Amulet.
     * 
     * @param items The list of items.
     * @return Returns true if the list of items contains the Amulet.
     */
    public static boolean hasAmulet(List<String> items) {
        return hasItem(items, AMULET);
    }
    
    /**
     * Checks if a list of items contains the Sword.
     * 
     * @param items The list of items.
     * @return Returns true if the list of items contains the Sword.
     */
    public static boolean hasSword(List<String> items) {
        return hasItem(items, SWORD);
    }
}
