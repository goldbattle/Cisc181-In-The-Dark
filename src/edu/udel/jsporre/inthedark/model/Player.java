package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public class Player implements IGameTile {

    private Position position;
    private Image image;
    
    /**
     * Default constructor for a player
     * A player has a position and an icon
     * 
     * @param position Top left position of the player
     */
    public Player(Position position) {
	this.position = position;
	this.image = new Image('O');
    }
    
    /* Generic Move methods */
    
    public void moveRight() {
	this.position.addColumn(1);
    }
    
    public void moveLeft() {
	this.position.addColumn(-1);
    }
    
    public void moveDown() {
	this.position.addRow(1);
    }
    
    public void moveUp() {
	this.position.addRow(-1);
    }
    
    /* Abstract methods */
    
    @Override
    public boolean canWalkOn() {
	return false;
    }
    
    @Override
    public Position getPostion() {
	return position;
    }

    @Override
    public Image getImage() {
	return image;
    }

}
