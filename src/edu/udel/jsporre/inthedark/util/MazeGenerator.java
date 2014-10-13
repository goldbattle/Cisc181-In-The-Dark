package edu.udel.jsporre.inthedark.util;

import java.util.ArrayList;
import java.util.List;

import edu.udel.jsporre.inthedark.model.*;
import edu.udel.jsporre.inthedark.game.*;

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
    
    
    public static void printDebug(int[][] maze){
        
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                //System.out.println("R: "+i+" C: "+j);
                System.out.print(maze[i][j]);
            }
            System.out.print("\n");
        }    
    }
    // Creates a list of possible places to move that follow our algorithm to create the maze
    public List<Position> getPossible(Position here){
        Position above = new Position(here.getX() + 1, here.getY());
        Position below = new Position(here.getX() - 1, here.getY());
        Position right = new Position(here.getX(), here.getY() + 1);
        Position left = new Position(here.getX(), here.getY() - 1);
        List possible = new ArrayList<Position>();
        if (isMovable(above) == true)
            possible.add(above);
        if (isMovable(below) == true)
            possible.add(below);
        if (isMovable(right) == true)
            possible.add(right);
        if (isMovable(left) == true)
            possible.add(left);
        return possible;
    }
    //Checks to see if a possible destination is ok to move to
    public boolean isMovable(Position place){
        if(place.getColumn() < 0 || place.getColumn() >= column)
            return false;
        // Compare to min max rows
        if(place.getRow() < 0 || place.getRow() >= row)
            return false;
        if (maze[place.getRow()][place.getColumn()] == 1){
            return false;
        }
        if (this.numberOfOnes(place) > 1){
            return false;
        }
        return true;
    }
    //Counts the number of ones that are around a possible destination
    public int numberOfOnes(Position place){
        Position above = new Position(place.getX() + 1, place.getY());
        Position below = new Position(place.getX() - 1, place.getY());
        Position right = new Position(place.getX(), place.getY() + 1);
        Position left = new Position(place.getX(), place.getY() - 1);
        int count = maze[above.getRow()][above.getColumn()] + maze[below.getRow()][below.getColumn()] + maze[right.getRow()][right.getColumn()] + maze[left.getRow()][left.getColumn()]; 
        return count;
    }
    
    
    
    

}
