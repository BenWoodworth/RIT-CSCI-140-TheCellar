/*
 * Trap.java
 * 
 * Version: 1
 * 
 * Revisions:
 *     Created basic class structure, and added subclasses and methods.
 *     Wrote apply methods for Weaken, Warp and Vanish.
 */

import java.util.List;


/**
 * A factory class for creating traps
 * 
 * @author Ben Woodworth
 */
public interface Trap {
    public static final String WEAKEN = "weaken";
    public static final String WARP = "warp";
    public static final String VANISH = "vanish";
    public static final String NONE = "none";

    /**
     * Apply this trap instance on the player
     * 
     * @param player The player to apply the trap to
     */
    public void apply(Player player);

    /**
     * Get the protection for this item
     * 
     * @return Returns the protection for this item
     */
    public String getProtection();

    /**
     * Creates a trap given a trap type and an optional parameter
     * 
     * @param type The type of trap
     * @param parameter The trap's parameter
     * @return Returns a new trap
     */
    public static Trap create(String type, int param, String protection) {
        switch (type) {
        case WEAKEN: return new Weaken(param, protection);
        case WARP:   return new Warp(param, protection);
        case VANISH: return new Vanish(protection);
        case NONE:   return null;
        }
        return null;
    }
    
    /**
     * Create a trap
     * 
     * @param type The type of trap to make.
     * @param param The parameter for the trap.
     * @param protection The item that protects the player from the trap.
     * @return Returns a new trap.
     */
    public static Trap create(String type, String param, String protection) {
        return create(type, Integer.parseInt(param), protection);
    }
    
    /**
     * Create a new trap without a parameter.
     * 
     * @param type The type of trap to make.
     * @param protection The item that protects the player from the trap.
     * @return Returns a new trap.
     */
    public static Trap create(String type, String protection) {
        return create(type, 0, protection);
    }

    /**
     * The types of traps.
     */
    class Weaken implements Trap {
        private int damage;
        private String protection;
        public Weaken(int damage, String protection) {
            this.damage = damage;
            this.protection = protection;
        }
        public void apply(Player player) {
            if (player.removeItem(protection)) {
                player.getMap().gameValues.setPlayerExp(player.getExp() + 1);
                player.output(
                        "You were protected from a Weaken trap!\n" +
                        "Protection removed: " + protection + "\n" +
                        "You gained one experience");
            } else {
                player.setHealth(player.getHealth() - damage);
                player.output(
                        "You ran into a Weaken trap!\n" +
                        "Damage taken: " + damage);
            }
        }
        public String getProtection() {
            return protection;
        }
    }
    class Warp implements Trap {
        private int warpRoom;
        private String protection;
        public Warp(int warpRoom, String protection) {
            this.warpRoom = warpRoom;
            this.protection = protection;
        }
        public void apply(Player player) {
            if (player.removeItem(protection)) {
                player.getMap().gameValues.setPlayerExp(player.getExp() + 1);
                player.output(
                        "You were protected from a Warp trap!\n" +
                        "Protection removed: " + protection + "\n" +
                        "You gained one experience");
            } else {
                player.warp(warpRoom);
                player.output(
                        "You ran into a Warp trap!\n" +
                        "You have been warped to another room");
            }
        }
        public int getWarpRoom() {
            return warpRoom;
        }
        public String getProtection() {
            return protection;
        }
    }
    class Vanish implements Trap {
        private String protection;
        public Vanish(String protection) {
            this.protection = protection;
        }
        public void apply(Player player) {
            if (player.removeItem(protection)) {
                player.getMap().gameValues.setPlayerExp(player.getExp() + 1);
                player.output(
                        "You were protected from a Vanish trap!\n" +
                        "Protection removed: " + protection + "\n" +
                        "You gained one experience");
            } else {
                List<String> remove = Items.getProtections(player.getItems());
                player.getItems().removeAll(remove);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < remove.size(); i++) {
                    if (i != 0) sb.append(", ");
                    sb.append(remove.get(i));
                }
                player.output(
                        "You ran into a Warp trap!\n" +
                        "You lost these protections: " + sb);
            }
        }
        public String getProtection() {
            return protection;
        }
    }
}
