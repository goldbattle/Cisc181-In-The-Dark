package edu.udel.jsporre.inthedark;

import junit.framework.TestCase;
import edu.udel.jsporre.inthedark.game.ActionPlayMove;
import edu.udel.jsporre.inthedark.game.MazeGame;
import edu.udel.jsporre.inthedark.model.PlayerDirection;
import edu.udel.jsporre.inthedark.util.Position;

public class MainTest extends TestCase {
    
    /**
     * Tests for the position equals method
     * This ensures that we have matching positions
     */
    public void test_position() {
        Position temp1 = new Position(0,0);
        Position temp2 = new Position(0,0);
        Position temp3 = new Position(0,1);
        Position temp4 = new Position(1,0);

        // Asserts
        assertTrue(temp1.equals(temp2));
        assertFalse(temp1.equals(temp3));
        assertFalse(temp1.equals(temp4));
    }

    /**
     * Tests if the player can move in a valid position
     * Moves the player, and checks for that mutation
     */
    public void test_canMove() {
        MazeGame game = new MazeGame();
        game.createDefaultGame();

        // Asserts
        assertFalse(game.canMove(new Position(0,0)));
        assertTrue(game.canMove(new Position(0,1)));

        // Update, move player down
        ActionPlayMove move = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move);

        // Assert
        assertTrue(game.canMove(new Position(0,0)));
        assertFalse(game.canMove(new Position(0,1)));
    }

    public void test_playerMove() {
        MazeGame game = new MazeGame();
        game.createDefaultGame();

        // Asserts
        assertFalse(game.canMove(new Position(0,0)));
        assertTrue(game.canMove(new Position(2,1)));

        // Update player, try to move into wall, move over one, down 2
        ActionPlayMove move0 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move0);
        ActionPlayMove move1 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move1);
        ActionPlayMove move2 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move2);
        ActionPlayMove move3 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move3);

        // Assert
        assertFalse(game.canMove(new Position(2,1)));
    }
    
    
    public static void main(String[] args) {

        // Create our manager
        MazeGame game = new MazeGame();
        game.createDefaultGame();
        
        // Init state
        System.out.println("Start Game:");
        System.out.println(game);
        
        // Move player some, for mid game state
        ActionPlayMove move0 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move0);
        ActionPlayMove move1 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move1);
        ActionPlayMove move2 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move2);
        System.out.println("Mid Game:");
        System.out.println(game);
        
        // End game
        ActionPlayMove move3 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move3);
        ActionPlayMove move4 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move4);
        ActionPlayMove move5 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move5);
        ActionPlayMove move6 = new ActionPlayMove(PlayerDirection.DIRECTION_UP);
        game.perform(move6);
        ActionPlayMove move7 = new ActionPlayMove(PlayerDirection.DIRECTION_UP);
        game.perform(move7);
        ActionPlayMove move8 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move8);
        ActionPlayMove move9 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move9);
        ActionPlayMove move10 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move10);
        ActionPlayMove move11 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move11);
        ActionPlayMove move12 = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
        game.perform(move12);
        
        ActionPlayMove move13 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move13);
        ActionPlayMove move14 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move14);
        ActionPlayMove move15 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move15);
        ActionPlayMove move16 = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
        game.perform(move16);

        System.out.println("End Game:");
        System.out.println(game);
        
    }
}
