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
        // Change our column
        int new_col = position.getColumn() + 1;
        // Move if you can
        if(GameManager.canMove(new Position(position.getRow(), new_col)))
            this.position.addColumn(1);
    }

    public void moveLeft() {
        // Change our column
        int new_col = position.getColumn() - 1;
        // Move if you can
        if(GameManager.canMove(new Position(position.getRow(), new_col)))
            this.position.addColumn(-1);
    }

    public void moveDown() {
        // Change our row
        int new_row = position.getRow() + 1;
        // Move if you can
        if(GameManager.canMove(new Position(new_row, position.getColumn())))
            this.position.addRow(1);
    }

    public void moveUp() {
        // Change our row
        int new_row = position.getRow() - 1;
        // Move if you can
        if(GameManager.canMove(new Position(new_row, position.getColumn())))
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
