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
        this.image = new Image("O");
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
    
    /**
     * Gets the position of the future player based on the direction passed
     * This method doesn't actually mutate any state.
     */
    public Position getNextPosition(PlayerDirection direction) {
        Position next = position;
        // Update based on direction
        switch(direction){
            case DIRECTION_UP:
                next = new Position(next.getRow()-1, next.getColumn());
                break;
            case DIRECTION_DOWN:
                next = new Position(next.getRow()+1, next.getColumn());
                break;
            case DIRECTION_RIGHT:
                next = new Position(next.getRow(), next.getColumn()+1);
                break;
            case DIRECTION_LEFT:
                next = new Position(next.getRow(), next.getColumn()-1);
                break;
        }
        return next;
    }

    /* Abstract methods */

    @Override
    public boolean canWalkOn() {
        return false;
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
