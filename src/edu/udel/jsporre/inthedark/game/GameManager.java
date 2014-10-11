package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;

import edu.udel.jsporre.inthedark.model.Finish;
import edu.udel.jsporre.inthedark.model.IGameTile;
import edu.udel.jsporre.inthedark.model.Player;
import edu.udel.jsporre.inthedark.model.Start;
import edu.udel.jsporre.inthedark.util.Position;

public class GameManager {

    private ArrayList<IGameTile> tiles;
    private Player player;
    private Finish finish;
    private Start start;


    public GameManager() {
	// Create our data
	tiles = new ArrayList<IGameTile>();

	// TODO: Create gui

	// TODO: Add events

	// TODO: Load maze generator

	// TODO: Load stats

	// Currently we generate static content
	player = new Player(new Position(2,2));
	start = new Start(new Position(0,0));
	finish = new Finish(new Position(9,9));
    }

    /**
     * This method checks to see if position in interest
     * can be moved to. If so true, else false
     * 
     * @param position The position to be checked
     * @return Boolean if possible or not
     */
    public static boolean canMove(Position position) {
	return false;
    }

    /**
     * Handles updating the player based on action
     * This should be called from a control/tilt listener
     */
    public void updatePlayer() {
	player.moveDown();
    }

    /**
     * Prints a debug layout of the grid in the console
     * This allows for handy debuging of the current game's state
     */
    public void printDebug() {
	IGameTile[][] temp = new IGameTile[10][10];

	// Print out our statics
	temp[player.getPostion().getRow()][player.getPostion().getColumn()] = player;
	temp[start.getPostion().getRow()][start.getPostion().getColumn()] = start;
	temp[finish.getPostion().getRow()][finish.getPostion().getColumn()] = finish;

	// Add tiles
	for(IGameTile tile : tiles) {
	    temp[tile.getPostion().getRow()][tile.getPostion().getColumn()] = tile;
	}

	// Print out the icon for each
	for(int j=0; j<temp[0].length; j++) {
	    for(int i=0; i<temp.length; i++) {
		if(temp[j][i] != null)
		    System.out.print(temp[j][i].getImage());
		else
		    System.out.print("  ");
	    }
	    System.out.print("\n");
	}
    }


}
