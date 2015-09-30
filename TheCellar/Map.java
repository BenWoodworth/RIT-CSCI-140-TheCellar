/*
 * Map.java
 * 
 * Version: 0
 * 
 * Revisions:
 *      Added public variables and wrote constructor method.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The Map class. Contains all the information about the game.
 * 
 * @author Ben Woodworth
 */
public class Map {
    public GameValues gameValues;
    public HashMap<String, Trap> traps;
    public Room[] rooms;
    public Hall[] halls;
    
    public Player player;
    public int curRoom;
    
    public Monster monster;
    public int monsterRoom;
    
    /**
     * Constructor for the Map class.
     * 
     * @param gameValues The values the Window will need access to.
     * @param file The file to load the maze from.
     * @throws IOException Throws an exception if the file can't be read.
     */
    public Map(GameValues gameValues, File file) throws IOException {
        this.gameValues = gameValues;
        
        player = new Player(this, 100, 1);
        curRoom = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(file));
        
        //Traps
        int trapCount = Integer.parseInt(br.readLine().split(" ")[0]);
        traps = new HashMap<String, Trap>();
        for (int i = 0; i < trapCount; i++) {
            String[] trap = br.readLine().split(" ");
            if (trap.length == 3)
                traps.put(trap[0], Trap.create(trap[1], trap[2]));
            else
                traps.put(trap[0], Trap.create(trap[1], trap[2], trap[3]));
        }
        
        //Rooms
        String roomInfo[] = br.readLine().split(" ");
        rooms = new Room[Integer.parseInt(roomInfo[0])];
        int amuletRoom = Integer.parseInt(roomInfo[1]);
        for (int i = 0; i < rooms.length; i++) {
            String trapName = br.readLine().split(" ")[0];
            Trap trap = null;
            if (!trapName.equalsIgnoreCase("none"))
                trap = traps.get(trapName);
            String items = br.readLine();
            String itemArr[] = new String[0];
            if (!items.equalsIgnoreCase("none"))
                itemArr = items.split(" ");
            rooms[i] = new Room(trap, itemArr);
            if (i == amuletRoom) rooms[i].getItems().add("Amulet");
        }
        
        //Halls
        halls = new Hall[Integer.parseInt(br.readLine().split(" ")[0])];
        for (int i = 0; i < halls.length; i++) {
            String[] hallInfo = br.readLine().split(" ");
            halls[i] = new Hall(hallInfo[0], hallInfo[1], hallInfo[2]);
        }
        
        //Monsters
        if (br.readLine().equals("1")) {
            String[] info = br.readLine().split(" ");
            monster = new Monster(this, info[0]);
            monsterRoom = Integer.parseInt(info[1]);
        }
        
        br.close();
        
        gameValues.setHalls(Hall.getConnectingHalls(curRoom, halls));
        gameValues.setPlayerItems(player.getItems());
        gameValues.setRoomItems(getCurrentRoom().getItems());
        gameValues.notifyObservers();
    }
    
    /**
     * Gets the Room that the player is currently in.
     * 
     * @return Returns the Room that the player is currently in.
     */
    public Room getCurrentRoom() {
        return rooms[curRoom];
    }
}
