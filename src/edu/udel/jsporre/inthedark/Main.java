package edu.udel.jsporre.inthedark;

import edu.udel.jsporre.inthedark.game.GameManager;

public class Main {

    public static void main(String[] args) {
	System.out.println("hello world!");

	// Create our manager
	GameManager manager = new GameManager();

	// Print debug
	manager.printDebug();
	manager.updatePlayer();
	manager.printDebug();
    }

}
