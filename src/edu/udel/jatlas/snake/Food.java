package edu.udel.jatlas.snake;

import edu.udel.jatlas.gameframework.Position;

public class Food {
    // these are constants in this simple snake game, but they could easily
    // be a property of Food, and different subclasses of Food might be worth
    // different points and make the snake grow by a different amount
    public static final int SNAKE_EAT_FOOD_SCORE = 1;
    public static final int SNAKE_EAT_FOOD_GROW = 1;
    
    private Position position;

    public Food(Position position) {
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }
    
    public char getSymbol() {
        return 'F';
    }

    public String toString() {
        return "Food at " + position.toString();
    }
}
