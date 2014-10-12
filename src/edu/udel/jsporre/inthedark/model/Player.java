package edu.udel.jsporre.inthedark.model;

import edu.udel.jsporre.inthedark.game.GameManager;
import edu.udel.jsporre.inthedark.util.Image;
import edu.udel.jsporre.inthedark.util.Position;

public class Player implements IGameTile {

    private Position position;
    private Image image;

    /**
     * Default constructor for a player A player has a position and an icon
     * 
     * @param position Top left position of the player
     */
    public Player(Position position) {
	this.position = position;
	this.image = new Image("O");
    }

    /* Generic Move methods */

    public void moveRight() {
        int new_row = position.getRow();
        int new_col = position.getColumn() + 1;
        // Move if you can
        if(GameManager.canMove(new Position(new_row, new_col)))
            this.position.addColumn(1);
    }

    public void moveLeft() {
        int new_row = position.getRow();
        int new_col = position.getColumn() - 1;
        // Move if you can
        if(GameManager.canMove(new Position(new_row, new_col)))
            this.position.addColumn(-1);
    }

    public void moveDown() {
        int new_row = position.getRow() + 1;
        int new_col = position.getColumn();
        // Move if you can
        if(GameManager.canMove(new Position(new_row, new_col)))
            this.position.addRow(1);
    }

    public void moveUp() {
        int new_row = position.getRow() - 1;
        int new_col = position.getColumn();
        // Move if you can
        if(GameManager.canMove(new Position(new_row, new_col)))
            this.position.addRow(-1);
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
