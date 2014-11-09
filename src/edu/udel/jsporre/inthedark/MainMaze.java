package edu.udel.jsporre.inthedark;

import edu.udel.jsporre.inthedark.util.MazeGenerator;
import edu.udel.jsporre.inthedark.util.Position;

public class MainMaze {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        
        Position start = new Position(0,0);
        Position finish = new Position(19,19);
        MazeGenerator maze = new MazeGenerator(start, finish, 20, 20);
        // Test debug loop
        for(int i=0; i<100; i++) {
            System.out.println("==Maze #"+i+"==");
            maze.recurseMaze();
            maze.printDebug();
            maze.resetMaze();
        }
    }

}
