package edu.udel.jsporre.inthedark;

import java.util.Scanner;

import edu.udel.jsporre.inthedark.game.GameManager;
import edu.udel.jsporre.inthedark.model.PlayerDirection;

public class Main {

    public static void main(String[] args) {

	// Debug
	System.out.println("Loading \"In the Dark\"");
	System.out.println("Version: " + GameManager.GAME_VERSION);
	double time_start = System.currentTimeMillis();

	// Create our manager
	new GameManager();

	System.out.println("Loaded in: " + (System.currentTimeMillis()-time_start) + "ms\n");
	
	// This is just for testing in the console
	Scanner scanner = new Scanner(System.in);
	String line_in = "";
	
	// Nice message
	System.out.println("Enter a direction [up,down,right,left].");
	System.out.println("Type \"exit\" to exit.");
	GameManager.printDebug();
	
	// Loop till we get the exit command
	while(!line_in.equals("exit")) {
	    // Read in input
	    line_in = scanner.nextLine();
	    // Find Directions
	    if(line_in.equals("up")){
		GameManager.updatePlayer(PlayerDirection.DIRECTION_UP);
	    } 
	    else if(line_in.equals("down")){
		GameManager.updatePlayer(PlayerDirection.DIRECTION_DOWN);
	    }
	    else if(line_in.equals("right")){
		GameManager.updatePlayer(PlayerDirection.DIRECTION_RIGHT);
	    }
	    else if(line_in.equals("left")){
		GameManager.updatePlayer(PlayerDirection.DIRECTION_LEFT);
	    }
	    // Print out the new state
	    GameManager.printDebug();
	}
    }

}
