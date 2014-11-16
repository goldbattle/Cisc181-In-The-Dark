package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;
import java.util.Random;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jsporre.inthedark.model.Wall;
import edu.udel.jsporre.inthedark.util.MazeGenerator;
import edu.udel.jsporre.inthedark.util.Position;


public class ActionCreateMaze implements Action<MazeGame>{
    
    private int rows;
    private int columns;
    
    /**
     * A maze is defined by the amount of rows/columns it has
     */
    public ActionCreateMaze(int rows, int columns) {
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
        Random r = new Random();
        Position start = new Position(0,r.nextInt(rows));
        Position finish = new Position(columns-1,r.nextInt(rows));
        MazeGenerator maze = new MazeGenerator(start, finish, rows, columns);
        // Recurse
        maze.recurseMaze();
        // Create walls
        ArrayList<Wall> temp = new ArrayList<Wall>();
        // Loop though the 2d array
        for (int i = 0; i < maze.getMaze().length; i++) {
            for (int j = 0; j < maze.getMaze()[i].length; j++) {
                if(maze.getMaze()[i][j] == 0) {
                    Position pos = new Position(i,j);
                    temp.add(new Wall(pos));
                }
            }
        }
        // Set the new content
        game.setMaze(temp);
        // Set start, finish and player positions
        game.setStart(start);
        game.setFinish(finish);
        game.setPlayer(start);
    }
    
    /**
     * To String method for debugging
     */
    public String toString() {
        return "CreateMazeAction [rows="+rows+", columns="+columns+"]";
    }

}
