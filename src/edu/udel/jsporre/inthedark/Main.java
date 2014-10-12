package edu.udel.jsporre.inthedark;

import edu.udel.jsporre.inthedark.game.GameManager;
import edu.udel.jsporre.inthedark.model.PlayerDirection;

public class Main {

    public static void main(String[] args) {

	// Debug
	System.out.println("Loading \"In the Dark\"");
	System.out.println("Version: " + GameManager.GAME_VERSION);
	double time_start = System.currentTimeMillis();
	
	// Create our manager
	GameManager manager = new GameManager();

	// Print debug
//	manager.printDebug();
//	GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
//	manager.printDebug();
//	GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
//	manager.printDebug();
//	GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
//	manager.printDebug();
	
	System.out.println("Loaded in: " + (System.currentTimeMillis()-time_start) + "ms");
    }

}
