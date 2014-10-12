package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;

import edu.udel.jsporre.inthedark.model.Finish;
import edu.udel.jsporre.inthedark.model.IGameTile;
import edu.udel.jsporre.inthedark.model.Player;
import edu.udel.jsporre.inthedark.model.PlayerDirection;
import edu.udel.jsporre.inthedark.model.Start;
import edu.udel.jsporre.inthedark.util.Position;

public class GameManager {
    
    // Constants
    public static final String GAME_VERSION = "v0.1";
    public static final int MAX_WORLD_HEIGHT = 100;
    public static final int MAX_WORLD_WIDTH = 100;    
    
    // Data
    private static ArrayList<IGameTile> tiles;
    private static Player player;
    private static Finish finish;
    private static Start start;
    private static int score;

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
	player = new Player(new Position(start.getPosition().getRow(), start.getPosition().getColumn()));
    }

    /**
     * This method checks to see if position in interest can be moved to. If so
     * true, else false
     * 
     * @param position The position to be checked
     * @return Boolean if possible or not
     */
    public static boolean canMove(Position position) {
	// Compare start
	if(start.getPosition().equals(position) && !start.canWalkOn())
	    return false;
	// Compare finish
	if(finish.getPosition().equals(position) && !finish.canWalkOn())
	    return false;
	// Compare other tiles
	for(IGameTile tile : tiles){
	    // Return false if you can't walk on it
	    if(tile.getPosition().equals(position) && !tile.canWalkOn())
		return false;
	}
	// All passed, can be walked on
	return true;
    }

    /**
     * Handles updating the player based on action This should be called from a
     * control/tilt listener
     */
    public static void updatePlayer(PlayerDirection direction) {
	// Move the player based on the given direction
	switch(direction){
	case DIRECTION_UP:
	    player.moveUp();
	    break;
	case DIRECTION_DOWN:
	    player.moveDown();
	    break;
	case DIRECTION_RIGHT:
	    player.moveRight();
	    break;
	case DIRECTION_LEFT:
	    player.moveLeft();
	    break;
	}
    }

    /**
     * Prints a debug layout of the grid in the console This allows for handy
     * debuging of the current game's state
     */
    public void printDebug() {
	
	System.out.println("=========================");
	
	IGameTile[][] temp = new IGameTile[10][10];

	// Print out our statics
	temp[start.getPosition().getRow()][start.getPosition().getColumn()] = start;
	temp[finish.getPosition().getRow()][finish.getPosition().getColumn()] = finish;
	temp[player.getPosition().getRow()][player.getPosition().getColumn()] = player;

	// Add tiles
	for (IGameTile tile : tiles) {
	    temp[tile.getPosition().getRow()][tile.getPosition().getColumn()] = tile;
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
