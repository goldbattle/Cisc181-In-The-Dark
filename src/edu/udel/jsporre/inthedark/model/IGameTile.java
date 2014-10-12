package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public interface IGameTile {

    /**
     * This method asks if the current tile can be walked on by a player
     * 
     * @return Boolean true if it can be walked on
     */
    public boolean canWalkOn();

    /**
     * This method allows the tile to get game ticks and update its settings
     * over time
     */
    // public void updateTick();

    /**
     * Returns the tile's current position, This is dictated as being the upper
     * left corner
     * 
     * @return Position of the tile
     */
    public Position getPosition();

    /**
     * Returns the tile's current image icon This is what icon should be
     * displayed on the screen
     * 
     * @return Image of the tile
     */
    public Image getImage();
}
