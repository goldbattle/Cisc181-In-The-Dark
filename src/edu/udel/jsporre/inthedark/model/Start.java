package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public class Start implements IGameTile {

    private Position position;
    private Image image;

    /**
     * Default constructor for a start block
     * Has a position and an image that is always assigned to this type
     * 
     * @param position Top left position of the start block
     */
    public Start(Position position) {
	this.position = position;
	this.image = new Image('S');
    }

    /* Abstract methods */

    @Override
    public boolean canWalkOn() {
	return true;
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
