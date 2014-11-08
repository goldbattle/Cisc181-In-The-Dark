package edu.udel.jsporre.inthedark.game;

import edu.udel.jatlas.gameframework.Action;


public class CreateMazeAction implements Action<MazeGame>{
    
    private int rows;
    private int columns;
    
    /**
     * A maze is defined by the amount of rows/columns it has
     */
    public CreateMazeAction(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }
    
    /**
     * It is always valid to create a new maze
     */
    public boolean isValid(MazeGame game) {
        return true;
    }

    /**
     * This method replaces the current tile array in the
     * `MazeGame` class so this maze becomes the new one.
     */
    public void update(MazeGame game) {
        // Call the maze generator here
        // Update the maze game tiles
    }
    
    /**
     * To String method for debugging
     */
    public String toString() {
        return "CreateMazeAction [rows="+rows+", columns="+columns+"]";
    }

}
