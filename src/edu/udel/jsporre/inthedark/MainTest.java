package edu.udel.jsporre.inthedark;

import junit.framework.TestCase;
import edu.udel.jsporre.inthedark.game.GameManager;
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
        new GameManager();

        // Asserts
        assertFalse(GameManager.canMove(new Position(0,0)));
        assertTrue(GameManager.canMove(new Position(0,1)));

        // Update, move player down
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);

        // Assert
        assertTrue(GameManager.canMove(new Position(0,0)));
        assertFalse(GameManager.canMove(new Position(0,1)));
    }

    public void test_playerMove() {
        new GameManager();

        // Asserts
        assertFalse(GameManager.canMove(new Position(0,0)));
        assertTrue(GameManager.canMove(new Position(2,1)));

        // Update player, try to move into wall, move over one, down 2
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);

        // Assert
        assertFalse(GameManager.canMove(new Position(2,1)));
    }
    
    
    public static void main(String[] args) {

        // Create our manager
        new GameManager();
        
        // Init state
        System.out.println("Start Game:");
        GameManager.printDebug();
        System.out.println();
        
        // Move player some, for mid game state
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        System.out.println("Mid Game:");
        GameManager.printDebug();
        System.out.println();
        
        // End game
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_UP);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_UP);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
        System.out.println("End Game:");
        GameManager.printDebug();
        System.out.println();
        
    }
}
