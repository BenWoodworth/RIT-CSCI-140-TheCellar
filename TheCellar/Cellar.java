/*
 * Cellar.java
 * 
 * Version: 0
 * 
 * Revisions:
 *      Wrote main(), startGame() and err() methods
 */

import java.io.File;
import java.io.IOException;

/**
 * The driver class for the game.
 * 
 * @author Ben Woodworth
 */
public class Cellar {
    /**
     * The main method, run from the command line.
     * 
     * @param args The filename of the maze file
     */
    public static void main(String[] args) {
        if (args.length != 1) err();
        try {
            startGame(args[0]);
        } catch (Exception e) {
            err();
        }
    }

    /**
     * Start the game given a filename.
     * 
     * @param file The file containing the maze information
     * @throws IOException Thrown if the file can't be found
     */
    private static void startGame(String file) throws IOException {
        GameValues gameValues = new GameValues();
        Map map = new Map(gameValues, new File(file));
        Window window = new Window(gameValues);
        new Controller(map, window);
        window.setVisible(true);
    }

    /**
     * Prints out an error message showing the usage for this program.
     */
    private static void err() {
        System.err.println("Usage: java Cellar config_file_name");
        System.exit(0);
    }
}
