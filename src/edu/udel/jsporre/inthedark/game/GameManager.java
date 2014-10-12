package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;

import edu.udel.jsporre.inthedark.model.Finish;
import edu.udel.jsporre.inthedark.model.IGameTile;
import edu.udel.jsporre.inthedark.model.Player;
import edu.udel.jsporre.inthedark.model.Start;
import edu.udel.jsporre.inthedark.util.Position;

public class GameManager {
    
    // Constants
    public static final String GAME_VERSION = "v0.1";
    public static final int MAX_WORLD_HEIGHT = 100;
    public static final int MAX_WORLD_WIDTH = 100;    
    
    // Data
    private ArrayList<IGameTile> tiles;
    private static Player player;
    private Finish finish;
    private Start start;
    private int score;

    public GameManager() {
	// Create our data
	tiles = new ArrayList<IGameTile>();

	// TODO: Create gui

	// TODO: Add events

	// TODO: Load maze generator

	// TODO: Load stats

	// Static content
	start = new Start(new Position(0, 0));
	finish = new Finish(new Position(9, 9));
	
	// Put the player ontop of the start
	player = new Player(new Position(start.getPostion().getRow(), start.getPostion().getColumn()));
    }

    /**
     * This method checks to see if position in interest can be moved to. If so
     * true, else false
     * 
     * @param position
     *            The position to be checked
     * @return Boolean if possible or not
     */
    public static boolean canMove(Position position) {
	return false;
    }

    /**
     * Handles updating the player based on action This should be called from a
     * control/tilt listener
     */
    public static void updatePlayer() {
	player.moveDown();
    }

    /**
     * Prints a debug layout of the grid in the console This allows for handy
     * debuging of the current game's state
     */
    public void printDebug() {
	
	System.out.println("=========================");
	
	IGameTile[][] temp = new IGameTile[10][10];

	// Print out our statics
	temp[start.getPostion().getRow()][start.getPostion().getColumn()] = start;
	temp[finish.getPostion().getRow()][finish.getPostion().getColumn()] = finish;
	temp[player.getPostion().getRow()][player.getPostion().getColumn()] = player;

	// Add tiles
	for (IGameTile tile : tiles) {
	    temp[tile.getPostion().getRow()][tile.getPostion().getColumn()] = tile;
	}

	// Print out the icon for each
	for (int i = 0; i < temp.length; i++) {
	    for (int j = 0; j < temp[0].length; j++) {
		//System.out.println("R: "+i+" C: "+j);
		if (temp[i][j] != null)
		    System.out.print(temp[i][j].getImage());
		else
		    System.out.print("  ");
	    }
	    System.out.print("\n");
	}
	
	System.out.println("=========================");
    }

}
