package edu.udel.jatlas.snake;

import edu.udel.jatlas.gameframework.Action;

/**
 * A player in the snake game can change the direction the
 * snake is heading. The player does not move the snake itself 
 * because that is time-based logic and will happen on
 * each tick of the game.
 *  
 * @author jatlas
 */
public class ChangeDirectionMove implements Action<SnakeGame> {
    private int direction;

    public ChangeDirectionMove(int direction) {
        this.direction = direction;
    }

    /**
     * It is valid to change direction in Snake to anything
     * (even if it is a bad idea to go backwards)
     */
    public boolean isValid(SnakeGame state) {
        return true;
    }

    public void update(SnakeGame game) {
        // does nothing in checkpoint 1
    }

    public int getDirection() {
        return direction;
    }

    public String toString() {
        return "ChangeDirectionMove [direction=" + Snake.DIRECTION_CHARS[direction] + "]";
    }
}
