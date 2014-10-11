package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public class Wall implements IGameTile {
    
    private Position position;
    private Image image;
    
    /**
     * Default constructor for a wall
     * Walls have a position and an image that is always assigned to type wall
     * 
     * @param position Top left position of the wall
     */
    public Wall(Position position) {
	this.position = position;
	this.image = new Image('#');
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
