package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public class Empty implements IGameTile {

    private Position position;
    private Image image;

    /**
     * Default constructor for an empty space
     * 
     * @param position Top left position of the space
     */
    public Empty(Position position) {
	this.position = position;
	this.image = new Image(" ");
    }

    /* Abstract methods */

    @Override
    public boolean canWalkOn() {
	return true;
    }

    @Override
    public Position getPosition() {
	return position;
    }

    @Override
    public Image getImage() {
	return image;
    }
}
