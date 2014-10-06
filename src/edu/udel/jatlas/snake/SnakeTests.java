package edu.udel.jatlas.snake;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;
import edu.udel.jatlas.gameframework.Position;

public class SnakeTests extends TestCase {
    // START -- these are factory methods that create test game states
    public static SnakeGame createStartGame() {
        return SnakeGame.makeDefaultStartGame();
    }
    
    public static SnakeGame createMidGame() {
        Snake snake = new Snake(new LinkedList<Position>(Arrays.asList(
            new Position(3, 2), new Position(3, 3))), Snake.DIRECTION_UP); 
        List<Wall> walls = SnakeGame.makeStartWalls();
        Food food = new Food(new Position(2, 2));
        return SnakeGame.makeStartGame(food, snake, walls);
    }
        
    public static SnakeGame createMidGame2() {
        Snake snake = new Snake(new LinkedList<Position>(Arrays.asList(
            new Position(5, 5), new Position(5, 6))), Snake.DIRECTION_LEFT); 
        List<Wall> walls = SnakeGame.makeStartWalls();
        Food food = new Food(new Position(2, 2));
        return SnakeGame.makeStartGame(food, snake, walls);
    }
    
    public static SnakeGame createAlmostEndGameWall() {
        Snake snake = new Snake(new LinkedList<Position>(Arrays.asList(
            new Position(3, 1), new Position(3, 2))), Snake.DIRECTION_UP); 
        List<Wall> walls = SnakeGame.makeStartWalls();
        Food food = new Food(new Position(2, 2));
        return SnakeGame.makeStartGame(food, snake, walls);
    }
    
    public static SnakeGame createEndGameWall() {
        Snake snake = new Snake(new LinkedList<Position>(Arrays.asList(
            new Position(3, 0), new Position(3, 1))), Snake.DIRECTION_UP);
        List<Wall> walls = SnakeGame.makeStartWalls();
        Food food = new Food(new Position(2, 2));
        return SnakeGame.makeStartGame(food, snake, walls);
    }
    
    public static SnakeGame createEndGameOverlap() {
        Snake snake = new Snake(new LinkedList<Position>(Arrays.asList(
            new Position(3, 3), new Position(3, 4), 
            new Position(4, 4), new Position(4, 3),
            new Position(3, 3))), Snake.DIRECTION_UP);
        List<Wall> walls = SnakeGame.makeStartWalls();
        Food food = new Food(new Position(2, 2));
        return SnakeGame.makeStartGame(food, snake, walls);
    }
    
    // END -- factory methods
    
    
    // START -- actual tests now
    
    public void test_isEnd() {
        assertFalse(createStartGame().isEnd());
        assertTrue(createEndGameWall().isEnd());
        assertTrue(createEndGameOverlap().isEnd());
    }
     
    // END -- actual tests
    
    public static void main(String[] args) {
        System.out.println(createStartGame());
        System.out.println(createMidGame());
        System.out.println(createEndGameWall());
        System.out.println(createEndGameOverlap());
        
        
        System.out.println(new ChangeDirectionMove(Snake.DIRECTION_UP));
        System.out.println(new ChangeDirectionMove(Snake.DIRECTION_DOWN));
        System.out.println(new ChangeDirectionMove(Snake.DIRECTION_LEFT));
        System.out.println(new ChangeDirectionMove(Snake.DIRECTION_RIGHT));
    }
}
