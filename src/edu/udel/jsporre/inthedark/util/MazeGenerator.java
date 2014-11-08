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
        maze[finish.getRow()][finish.getColumn()] = 2;
        
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
    //Checks to see if a possible destination is ok to move to
    public boolean isMovable(Position place){
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
    //Counts the number of ones that are around a possible destination
    public int numberOfOnes(Position place){
    	int count = 0;
    	if(place.getRow() - 1 >-1){
    		Position above = new Position(place.getRow() - 1, place.getColumn());
    		count += maze[above.getRow()][above.getColumn()];
    	}
    	if(place.getRow() + 1 < maze.length){
    		Position below = new Position(place.getRow() + 1, place.getColumn());
    		count += maze[below.getRow()][below.getColumn()];
    	}
    	if(place.getColumn() + 1 < maze[place.getRow()].length){
    		Position right = new Position(place.getRow(), place.getColumn() + 1);
    		count += maze[right.getRow()][right.getColumn()];
    	}
    	if(place.getColumn() - 1 > -1){
    		Position left = new Position(place.getRow(), place.getColumn() - 1);
    		count += maze[left.getRow()][left.getColumn()];
    	}
        return count;
    }
    
    
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
    
    public void recurseMaze(Position place){
    	maze[place.getRow()][place.getColumn()] = 1;
    	if(nextToTwo(place)){
    		return;
    	}
    	List<Position> possible = getPossible(place);
    	Position temp = null;
    	for(Position possiblePlace : possible){
    		if(nextToTwo(possiblePlace)){
    			//recurseMaze(possiblePlace);
    			temp = possiblePlace;
    		}
    	}
    	if(temp != null){
    		recurseMaze(temp);
    	}
    	
    }
    
    
    

}
