package edu.udel.jatlas.snake;

import java.util.LinkedList;

import edu.udel.jatlas.gameframework.Position;

/**
 * A Snake contains a linked list of Positions, a direction,
 * and a number of segments that it should grow.
 * 
 * @author jatlas
 */
public class Snake {
    // this is an enum pattern but I don't want to add to our confusion
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;
    public static final char[] DIRECTION_CHARS = {'^','>','v','<'};
    
    private LinkedList<Position> segments;
    private int direction;
    private int growOnNextTick; // if the snake is supposed to grow, this is how much it has left to grow

    public Snake(LinkedList<Position> segments, int direction) {
        this(segments, direction, 0);
    }

    public Snake(LinkedList<Position> segments, int direction, int growOnNextTick) {
        this.segments = segments;
        this.direction = direction;
    }

    public int getDirection() {
        return direction;
    }
    
    public int getOppositeDirection() {
        return (direction + 2) % 4;
    }
    
    public char getDirectionChar() {
        return DIRECTION_CHARS[direction];
    }

    public LinkedList<Position> getSegments() {
        return segments;
    }

    public int getGrowOnNextTick() {
        return growOnNextTick;
    }
   
    /**
     * Gets the position the snake will move its head to next if
     * it was to move in direction.
     * This method doesn't actually mutate any state.
     * 
     * @return
     */
    public Position getNextPosition(int direction) {
        Position head = segments.getFirst();
        Position next = head;
        
        if (direction == DIRECTION_UP) {
            next = new Position(head.getColumn(), head.getRow() - 1);
        }
        else if (direction == DIRECTION_RIGHT) {
            next = new Position(head.getColumn() + 1, head.getRow());
        }
        else if (direction == DIRECTION_DOWN) {
            next = new Position(head.getColumn(), head.getRow() + 1);
        }
        else if (direction == DIRECTION_LEFT) {
            next = new Position(head.getColumn() - 1, head.getRow());
        }
        
        return next;
    }
}
