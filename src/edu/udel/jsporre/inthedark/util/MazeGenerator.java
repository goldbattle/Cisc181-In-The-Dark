package edu.udel.jsporre.inthedark.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MazeGenerator {
    private Position start;
    private Position finish;
    private int row;
    private int column;
    private int[][] maze;

    public MazeGenerator(Position start, Position finish, int row, int column){
        this.start = start;
        this.finish = finish;
        this.row = row;
        this.column = column;
        this.maze = new int[row][column];        
    }

    public int[][] getMaze(){
        return maze;
    }

    public void resetMaze() {
        this.maze = new int[row][column];
    }

    /**
     * Prints a nice debug image of the maze
     */
    public void printDebug(){
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                //System.out.println("R: "+i+" C: "+j);
                System.out.print(maze[i][j]);
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

    /**
     * Public function that starts the recursion of the maze array
     */
    public void recurseMaze() {
        // Add the finish
        maze[finish.getRow()][finish.getColumn()] = 2;
        // Recurse from the start
        this.recurseMaze(start);
    }

    /**
     * Comment here..
     * Private recursive function that loops through every space from a start position
     * 
     * @param place
     */
    private void recurseMaze(Position place){
        // Make sure we are not ontop of the finish
        if(maze[place.getRow()][place.getColumn()] == 2) {
            return;
        }

        // Make the tile into a 1, our representation of a path
        maze[place.getRow()][place.getColumn()] = 1;

        // Checks to see if it is next to the finish
        if(nextToTwo(place)){
            return;
        }
        // Get the possible positions to move to
        List<Position> possible = getPossible(place);

        // If no possible moves, then end method
        if (possible.size() == 0){
            return;
        }

        // If there are possible positions, choose one
        Random r = new Random();
        int randomInt = r.nextInt(possible.size());
        Position nextMove = possible.remove(randomInt);
        recurseMaze(nextMove);

        // Check the rest of the positions, if we have them
        if (possible.size() > 0){
            recurseMaze(place);
        }
    }

    /**
     * Comment here...
     * Creates a list of possible places to move that follow our algorithm to create the maze
     * 
     * @param here
     * @return
     */
    private List<Position> getPossible(Position here){

        List<Position> possible = new ArrayList<Position>();
        Position above = new Position(here.getRow() - 1, here.getColumn());
        Position below = new Position(here.getRow() + 1, here.getColumn());
        Position right = new Position(here.getRow(), here.getColumn() + 1);
        Position left = new Position(here.getRow(), here.getColumn() - 1);
        if(isMovable(above)){
            possible.add(above);
        }
        if(isMovable(below)){
            possible.add(below);
        }
        if(isMovable(right)){
            possible.add(right);
        }
        if(isMovable(left)){
            possible.add(left);
        }
        return possible;
    }

    /**
     * Comment here...
     * Checks to see if a possible destination is ok to move to
     * 
     * @param place
     * @return
     */
    private boolean isMovable(Position place){
        // compare to min max columns
        if(place.getColumn() < 0 || place.getColumn() >= column)
            return false;
        // Compare to min max rows
        if(place.getRow() < 0 || place.getRow() >= row)
            return false;
        //Checks to see if it is a space already used, or "walked on"
        if (maze[place.getRow()][place.getColumn()] == 1){
            return false;
        }
        //Checks the number of already used places around it
        if (this.numberOfOnes(place) > 1){
            return false;
        }
        return true;
    }

    /**
     * Comment here...
     * Counts the number of ones that are around a possible destination
     * 
     * @param place
     * @return
     */
    private int numberOfOnes(Position place){
        int count = 0;
        if(place.getRow() - 1 >-1){
            Position above = new Position(place.getRow() - 1, place.getColumn());
            // Check
            if(maze[above.getRow()][above.getColumn()] == 1) {
                count++;
            }
        }
        if(place.getRow() + 1 < maze.length){
            Position below = new Position(place.getRow() + 1, place.getColumn());
            // Check
            if(maze[below.getRow()][below.getColumn()] == 1) {
                count++;
            }
        }
        if(place.getColumn() + 1 < maze[place.getRow()].length){
            Position right = new Position(place.getRow(), place.getColumn() + 1);
            // Check
            if(maze[right.getRow()][right.getColumn()] == 1) {
                count++;
            }
        }
        if(place.getColumn() - 1 > -1){
            Position left = new Position(place.getRow(), place.getColumn() - 1);
            // Check
            if(maze[left.getRow()][left.getColumn()] == 1) {
                count++;
            }
        }
        return count;
    }

    /**
     * Comment here..
     * 
     * @param place
     * @return
     */
    public boolean nextToTwo(Position place){
        if(place.getRow() - 1 > -1){
            Position above = new Position(place.getRow() - 1, place.getColumn());
            return (maze[above.getRow()][above.getColumn()] == 2);
        }

        else if(place.getRow() + 1 < maze.length){
            Position below = new Position(place.getRow() + 1, place.getColumn());
            return(maze[below.getRow()][below.getColumn()] == 2);

        }
        else if(place.getColumn() + 1 < maze[place.getRow()].length){
            Position right = new Position(place.getRow(), place.getColumn() + 1);
            return(maze[right.getRow()][right.getColumn()] == 2);
        }
        else if(place.getColumn() - 1 > -1){
            Position left = new Position(place.getRow(), place.getColumn() - 1);
            return(maze[left.getRow()][left.getColumn()] == 2);
        }
        return false;

    }
}
