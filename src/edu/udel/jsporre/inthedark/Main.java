package edu.udel.jsporre.inthedark;

import edu.udel.jatlas.gameframework.ConsoleListener;
import edu.udel.jatlas.gameframework.JavaTicker;
import edu.udel.jsporre.inthedark.game.ActionCreateMaze;
import edu.udel.jsporre.inthedark.game.MazeAI;
import edu.udel.jsporre.inthedark.game.MazeGame;

public class Main {

    public static void main(String[] args) {

        // Debug
        System.out.println("Loading \"In the Dark\"");
        double time_start = System.currentTimeMillis();

        // Create our manager
        MazeGame game = new MazeGame();
        //game.createDefaultGame();
        game.perform(new ActionCreateMaze(10, 10));
        game.addGameListener(new ConsoleListener());
        game.addGameListener(new MazeAI(game));
        game.start(new JavaTicker());
        
        // Done
        System.out.println("Loaded in: " + (System.currentTimeMillis()-time_start) + "ms\n");

    }

}
