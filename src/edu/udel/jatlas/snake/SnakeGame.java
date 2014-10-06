package edu.udel.jatlas.snake;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import edu.udel.jatlas.gameframework.Game;
import edu.udel.jatlas.gameframework.Position;

/**
 * The state of the Snake game.
 * 
 * @author jatlas
 */
public class SnakeGame extends Game {
    // rule constants
    public static final int SNAKE_WORLD_HEIGHT = 24;
    public static final int SNAKE_WORLD_WIDTH = 16;
    public static final int SNAKE_MAX_LENGTH_PER_LEVEL = 13;
    public static final int SNAKE_LENGTH_START = 3;
    public static final int SNAKE_LENGTH_INCREASE = 1;
    public static final float SPEED_INCREASE = 0.2f;
    public static final long SPEED_START = 200;

    // the basic objects for Snake
    private Food food;
    private Snake snake;
    private List<Wall> walls;

    // the accumulation of score the player has earned
    private int score;

    // the speed is the number of milliseconds between state updates
    // due to progression of the game. The lower this value, the faster
    // the model will update (later when we add onTick).
    private long speed;

    // number of rows in the "world"
    private int rows;
    // number of columns in the "world"
    private int cols;

    // level
    private int level;

    public SnakeGame(Food food, Snake snake, List<Wall> walls, 
	    int score, long speed, int rows, int cols, int level) {
	this.food = food;
	this.snake = snake;
	this.walls = walls;
	this.score = score;
	this.speed = speed;
	this.rows = rows;
	this.cols = cols;
	this.level = level;
    }

    public int getScore() {
	return score;
    }

    public long getSpeed() {
	return speed;
    }

    public int getRows() {
	return rows;
    }

    public int getCols() {
	return cols;
    }

    public Food getFood() {
	return food;
    }

    public Snake getSnake() {
	return snake;
    }

    public List<Wall> getWalls() {
	return walls;
    }

    /**
     * Convenience method.
     * 
     * @param p
     * @return
     */
     public boolean isWall(Position p) {
	 return wallsContain(walls, p);
     }

     /**
      * Returns a "visual" representation of a Snake game
      */
     public String toString() {
	 // build a grid first and then print the grid
	 char[][] grid = new char[rows][cols]; 
	 // initialize the grid to all spaces
	 for (char[] row : grid) {
	     Arrays.fill(row, ' ');
	 }
	 for (Wall wall : walls) {
	     for (Position p : wall) {
		 grid[p.getRow()][p.getColumn()] = 'W';
	     }
	 }

	 // place the rest of the snake segments in the grid
	 for (Position p : getSnake().getSegments().subList(1, getSnake().getSegments().size())) {
	     grid[p.getRow()][p.getColumn()] = 'O';
	 }

	 // place the head snake character in the grid
	 //  (doing this after the body so we can see it even if there is a collision)
	 Position head = getSnake().getSegments().getFirst();
	 int hr = head.getRow();
	 int hc = head.getColumn();
	 // collision will show as a #
	 grid[hr][hc] = (grid[hr][hc] == ' ') ? getSnake().getDirectionChar() : '#';

	 // place the food in the grid
	 int fr = food.getPosition().getRow();
	 int fc = food.getPosition().getColumn();
	 // "eaten" food will show up as an E
	 grid[fr][fc] = (grid[fr][fc] == ' ') ? food.getSymbol() : 'E';

	 // build a string from the grid
	 StringBuilder buffer = new StringBuilder();
	 buffer.append(getStatus()).append("\n");
	 for (char[] row : grid) {
	     buffer.append(row);
	     buffer.append('\n'); // new line
	 }
	 return buffer.toString();
     }

     public String getStatus() {
	 return "Level " + getLevel() + "  Score " + getScore();
     }

     /**
      * Helper method that returns true if any of the given walls contain the
      * given position.
      * 
      * @return
      */
     public static boolean wallsContain(List<Wall> walls, Position p) {
	 for (Wall wall : walls) {
	     if (wall.contains(p)) {
		 return true;
	     }
	 }
	 return false;
     }
     /**
      * Is the state an ending state of the game? In Snake this happens when the
      * snake has run into a wall or itself.
      * 
      * @return
      */
     public boolean isEnd() {
	 // check wall
	 LinkedList<Position> segments = snake.getSegments();
	 Position head = segments.getFirst();
	 if (wallsContain(walls, head)) {
	     return true;
	 }

	 // check other segments
	 for (Position p : segments) {
	     // if p is not the head but has the same exact Position
	     if (p != head && p.equals(head)) {
		 return true;
	     }
	 }
	 return false;
     }

     public int getLevel() {
	 return level;
     }


     public static Snake makeStartSnake() {
	 // make new Snake
	 LinkedList<Position> segments = new LinkedList<Position>();
	 for (int i = 0; i < SNAKE_LENGTH_START; i++) {
	     segments.add(new Position(SNAKE_WORLD_WIDTH / 2, 
		     SNAKE_WORLD_HEIGHT / 2 + i));
	 }
	 return new Snake(segments, Snake.DIRECTION_UP);
     }

     public static List<Wall> makeStartWalls() {
	 // make 4 walls
	 ArrayList<Wall> walls = new ArrayList<Wall>();
	 walls.add(new Wall(new Position(0,0), new Position(SNAKE_WORLD_WIDTH-1, 0))); // top
	 walls.add(new Wall(new Position(0,SNAKE_WORLD_HEIGHT-1), 
		 new Position(SNAKE_WORLD_WIDTH-1, SNAKE_WORLD_HEIGHT-1))); // bottom
	 walls.add(new Wall(new Position(0,0), new Position(0, SNAKE_WORLD_HEIGHT-1))); // left
	 walls.add(new Wall(new Position(SNAKE_WORLD_WIDTH-1,0), 
		 new Position(SNAKE_WORLD_WIDTH-1, SNAKE_WORLD_HEIGHT-1))); // right
	 return walls;
     }

     public static Food makeRandomFood(Snake snake, List<Wall> walls) {
	 // make 1 random food that does not collide with the given snake or walls
	 Food food = null;
	 while (food == null) {
	     int row = (int)(Math.random()*SNAKE_WORLD_HEIGHT);
	     int col = (int)(Math.random()*SNAKE_WORLD_WIDTH);
	     Position p = new Position(col, row);
	     if (!wallsContain(walls, p) && !snake.getSegments().contains(p)) {
		 food = new Food(p);
	     }
	 }
	 return food;
     }

     /**
      * Makes a default starting game
      * @return
      */
     public static SnakeGame makeDefaultStartGame() {
	 Snake snake = makeStartSnake();
	 List<Wall> walls = makeStartWalls();
	 Food food = makeRandomFood(snake, walls);
	 return makeStartGame(food, snake, walls);
     }

     /**
      * Makes a starting game with the given food, snake, and walls
      */
     public static SnakeGame makeStartGame(Food food, Snake snake, List<Wall> walls) {
	 return new SnakeGame(food, snake, walls,
		 0, SPEED_START, SNAKE_WORLD_HEIGHT, SNAKE_WORLD_WIDTH, 0);
     }


}
