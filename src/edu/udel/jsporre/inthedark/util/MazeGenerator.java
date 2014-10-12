package edu.udel.jsporre.inthedark.util;

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
    
    public boolean getPossible(Position here){
        
        
        
        
        return false;
    }
    
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
    
    public int numberOfOnes(Position place){
        return 1;
    }
    
    
    
    

}
