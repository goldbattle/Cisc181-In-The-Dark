package edu.udel.jsporre.inthedark.game;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jsporre.inthedark.model.PlayerDirection;
import edu.udel.jsporre.inthedark.util.Position;

public class ActionPlayMove implements Action<MazeGame>{
    
    private PlayerDirection direction;
    
    /**
     * A Player move is defined by a new position that a player
     * wants to move to.
     */
    public ActionPlayMove(PlayerDirection direction) {
        this.direction = direction;
    }
    
    /**
     * Checks if the move location is a valid move
     */
    public boolean isValid(MazeGame game) {
        // Get current position
        Position temp = game.getPlayer().getPosition();
        // Check future position
        switch(direction){
            case DIRECTION_UP:
                return game.canMove(new Position(temp.getRow()-1, temp.getColumn()));
            case DIRECTION_DOWN:
                return game.canMove(new Position(temp.getRow()+1, temp.getColumn()));
            case DIRECTION_RIGHT:
                return game.canMove(new Position(temp.getRow(), temp.getColumn()+1));
            case DIRECTION_LEFT:
                return game.canMove(new Position(temp.getRow(), temp.getColumn()-1));
        }
        return false;
    }

    /**
     * Moves the player to the new position
     */
    public void update(MazeGame game) {
        switch(direction){
            case DIRECTION_UP:
                game.getPlayer().moveUp();
                break;
            case DIRECTION_DOWN:
                game.getPlayer().moveDown();
                break;
            case DIRECTION_RIGHT:
                game.getPlayer().moveRight();
                break;
            case DIRECTION_LEFT:
                game.getPlayer().moveLeft();
                break;
        }
    }
    
    /**
     * To String method for debugging
     */
    public String toString() {
        return "PlayMoveAction [direction="+direction+"]";
    }

}
