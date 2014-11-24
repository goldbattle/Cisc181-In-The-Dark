package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;

import edu.udel.jatlas.gameframework.Game;
import edu.udel.jatlas.gameframework.Tickable;
import edu.udel.jsporre.inthedark.model.Finish;
import edu.udel.jsporre.inthedark.model.IGameTile;
import edu.udel.jsporre.inthedark.model.Player;
import edu.udel.jsporre.inthedark.model.Start;
import edu.udel.jsporre.inthedark.model.Wall;
import edu.udel.jsporre.inthedark.util.Position;

public class MazeGame extends Game implements Tickable {


    // Constants
    public static final String GAME_VERSION = "v0.3";
    public static final int MAX_WORLD_HEIGHT = 100;
    public static final int MAX_WORLD_WIDTH = 100;
    public static int COLUMNS = 10;
    public static int ROWS = 10;

    // Data
    private static ArrayList<IGameTile> tiles;
    private static Player player;
    private static Finish finish;
    private static Start start;
    private static int score;
    private static int max_score;
    private static double time;

    public MazeGame() {
        // Create our data
        tiles = new ArrayList<IGameTile>();
        score = 0;
        max_score = 0;
    }

    /**
     * This method creates the default game state
     * This is just for testing purposes, in the real game it should call the maze generator
     */
    public void createDefaultGame() {
        // Set or size
        COLUMNS = 10;
        ROWS = 5;
        
        // Load tiles
        tiles.add(new Wall(new Position(1,0)));
        tiles.add(new Wall(new Position(1,2)));
        tiles.add(new Wall(new Position(0,2)));
        tiles.add(new Wall(new Position(2,0)));
        tiles.add(new Wall(new Position(2,2)));
        tiles.add(new Wall(new Position(3,0)));

        tiles.add(new Wall(new Position(4,0)));
        tiles.add(new Wall(new Position(4,1)));
        tiles.add(new Wall(new Position(4,2)));
        tiles.add(new Wall(new Position(4,3)));

        tiles.add(new Wall(new Position(4,4)));
        tiles.add(new Wall(new Position(3,4)));
        tiles.add(new Wall(new Position(2,4)));
        tiles.add(new Wall(new Position(0,2)));
        tiles.add(new Wall(new Position(0,4)));

        tiles.add(new Wall(new Position(0,5)));
        tiles.add(new Wall(new Position(0,6)));
        tiles.add(new Wall(new Position(1,6)));
        tiles.add(new Wall(new Position(2,6)));

        // Static content (make this dynamic in the maze if needed)
        start = new Start(new Position(0, 0));
        finish = new Finish(new Position(ROWS-1, COLUMNS-1));

        // Put the player ontop of the start
        player = new Player(new Position(start.getPosition().getRow(), start.getPosition().getColumn()));
    }
    
    /**
     * On tick, this handles updating the different tick methods
     */
    public void onTick() {
        time += getRealTimeTickLength()/1000;
    }
    
    /**
     * Gets the real tick length/fps
     */
    public long getRealTimeTickLength() {
        return 200;
    }
    
    /**
     * This method checks to see if position in interest can be moved to. 
     * If so true, else false
     * 
     * @param position The position to be checked
     * @return Boolean if possible or not
     */
    public boolean canMove(Position position) {
        // Compare tiles
        for(IGameTile tile : tiles){
            // Return false if you can't walk on it
            if(tile.getPosition().equals(position) && !tile.canWalkOn())
                return false;
        }
        // Compare Player
        if(player.getPosition().equals(position) && !player.canWalkOn())
            return false;
        // Compare start
        if(start.getPosition().equals(position) && !start.canWalkOn())
            return false;
        // Compare finish
        if(finish.getPosition().equals(position) && !finish.canWalkOn())
            return false;
        // Compare to min max columns
        if(position.getColumn() < 0 || position.getColumn() >= COLUMNS)
            return false;
        // Compare to min max rows
        if(position.getRow() < 0 || position.getRow() >= ROWS)
            return false;
        // All passed, can be walked on
        return true;
    }
    
    public int amountOfExits(Position pos) {
        // Master count var
        int count = 4;
        // Possible move positions
        Position up = new Position(pos.getRow()-1, pos.getColumn());
        Position down = new Position(pos.getRow()+1, pos.getColumn());
        Position left = new Position(pos.getRow(), pos.getColumn()-1);
        Position right = new Position(pos.getRow(), pos.getColumn()+1);
        // Subtract one from count if it is a wall
        for(IGameTile tile : tiles) {
            if(tile.getPosition().equals(up))
                count--;
            if(tile.getPosition().equals(down))
                count--;
            if(tile.getPosition().equals(left))
                count--;
            if(tile.getPosition().equals(right))
                count--;
        }
        // Compare to min max distances
        if(up.getColumn() < 0 || up.getColumn() >= COLUMNS || up.getRow() < 0 || up.getRow() >= ROWS)
            count--;
        // Compare to min max distances
        if(down.getColumn() < 0 || down.getColumn() >= COLUMNS || down.getRow() < 0 || down.getRow() >= ROWS)
            count--;
        // Compare to min max distances
        if(left.getColumn() < 0 || left.getColumn() >= COLUMNS || left.getRow() < 0 || left.getRow() >= ROWS)
            count--;
        // Compare to min max distances
        if(right.getColumn() < 0 || right.getColumn() >= COLUMNS || right.getRow() < 0 || right.getRow() >= ROWS)
            count--;
        // Return
        return count;
    }

    /**
     * Checks if the game has ended
     * The game has ended if the player is on top of the finish square
     * 
     * @return Boolean state of the game
     */
    public boolean isEnd() {
        if(player == null)
            return false;
        if(finish == null)
            return false;
        if(player.getPosition().equals(finish.getPosition())) {
            score++;
            if(score > max_score)
                max_score = score;
            return true;
        }
        return false;
    }

    /**
     * Prints a debug layout of the grid in the console
     * This allows for handy debugging of the current game's state
     * This acts as our toString method for the class, and classes below
     */
    public String toString() {
        
        // Data needed
        String result = "==========\n";
        IGameTile[][] temp = new IGameTile[ROWS][COLUMNS];

        // Add tiles
        for (IGameTile tile : tiles) {
            temp[tile.getPosition().getRow()][tile.getPosition().getColumn()] = tile;
        }

        // Add out our statics
        if(start != null)
            temp[start.getPosition().getRow()][start.getPosition().getColumn()] = start;
        if(finish != null)
            temp[finish.getPosition().getRow()][finish.getPosition().getColumn()] = finish;
        if(player != null)
            temp[player.getPosition().getRow()][player.getPosition().getColumn()] = player;

        // Print out the icon for each
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                //System.out.println("R: "+i+" C: "+j);
                if (temp[i][j] != null)
                    result += temp[i][j].getImage();
                else
                    result += " ";
            }
            result += "\n";
        }
        result += "==========";
        // Return
        return result;
    }
    
    /**
     * Get the player object
     * @return Player of the game
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Get the Start object
     * @return Start of the game
     */
    public Start getStart() {
        return start;
    }
    
    /**
     * Get the Finish object
     * @return Finish of the game
     */
    public Finish getFinish() {
        return finish;
    }

    /**
     * Resets the walls of the maze
     * 
     * @param walls The walls of the maze
     */
    public void setMaze(ArrayList<Wall> walls) {
        tiles.clear();
        tiles.addAll(walls);
    }
    
    /**
     * Reset the start position
     */
    public void setStart(Position start2) {
        start = new Start(start2);
        
    }

    /**
     * Reset the finish position
     */
    public void setFinish(Position finish2) {
        finish = new Finish(finish2);
        
    }
    
    /**
     * Reset the player position
     */
    public void setPlayer(Position player2) {
        player = new Player(player2);        
    }

    public String getStatus() {
        return "Score: "+score+"/"+max_score;
    }
    
    public String getTimer() {
        return (int)time + "s";
    }
    
}
