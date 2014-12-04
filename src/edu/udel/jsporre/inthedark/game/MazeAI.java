package edu.udel.jsporre.inthedark.game;

import java.util.ArrayList;
import java.util.List;

import edu.udel.jatlas.gameframework.AI;
import edu.udel.jatlas.gameframework.Action;
import edu.udel.jsporre.inthedark.model.Player;
import edu.udel.jsporre.inthedark.model.PlayerDirection;
import edu.udel.jsporre.inthedark.util.Position;

public class MazeAI extends AI<MazeGame> {
    
    // List of pass locations
    List<Position> visited_locations;
    
    /**
     * Default constructor
     * 
     * @param game MazeGame class
     */
    public MazeAI(MazeGame game) {
        super("Maze AI");
        visited_locations = new ArrayList<Position>();
    }

    /**
     * Returns a list of all valid moves from a given state.
     */
    public List<Action<MazeGame>> getAllValidActions(MazeGame game) {
        // Array that contains our actions
        List<Action<MazeGame>> validMoves = new ArrayList<Action<MazeGame>>();
        // Only add valid actions
        if (!game.isEnd()) {
          ActionPlayMove down = new ActionPlayMove(PlayerDirection.DIRECTION_DOWN);
          ActionPlayMove up = new ActionPlayMove(PlayerDirection.DIRECTION_UP);
          ActionPlayMove right = new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT);
          ActionPlayMove left = new ActionPlayMove(PlayerDirection.DIRECTION_LEFT);
          // Only add if valid
          if(down.isValid(game))
              validMoves.add(down);
          if(up.isValid(game))
              validMoves.add(up);
          if(right.isValid(game))
              validMoves.add(right);
          if(left.isValid(game))
              validMoves.add(left);
        }
        // Return
        return validMoves;
    }
    
    /**
     * Handles updating the AI after a successful event
     */
    public void onPerformActionEvent(Action<MazeGame> action, MazeGame game) {
	// Only handle our actions
	if(!(action instanceof ActionPlayMove))
	    return;
        // Get current player, and action
        Player player = game.getPlayer();
        ActionPlayMove m = (ActionPlayMove)action;
        // Get the last position
        switch(m.getDirection()){
            case DIRECTION_UP:
                visited_locations.add(player.getNextPosition(PlayerDirection.DIRECTION_DOWN));
                break;
            case DIRECTION_DOWN:
                visited_locations.add(player.getNextPosition(PlayerDirection.DIRECTION_UP));
                break;
            case DIRECTION_RIGHT:
                visited_locations.add(player.getNextPosition(PlayerDirection.DIRECTION_LEFT));
                break;
            case DIRECTION_LEFT:
                visited_locations.add(player.getNextPosition(PlayerDirection.DIRECTION_RIGHT));
                break;
        }
    }
    
    /**
     * Calculates a heuristic score based on distance and other details
     * 
     * If the game is over return -1
     * If you can not move in that direction 0
     * If the next is the finish, always move towards it
     * If you have not visited the location, go there
     */
    public double getHeuristicScore(Action<MazeGame> action, MazeGame game) {
        if (game.isEnd()) {
            return -1;
        }
        // Convert the action to our ActionPlayMove
        ActionPlayMove m = (ActionPlayMove)action;
       
        // Get the player
        Player player = game.getPlayer();
        Position next = player.getNextPosition(m.getDirection());
        if (!game.canMove(next)) {
            return 0;
        }
        // Always move to the finish
        if(next.equals(game.getFinish().getPosition())) {
            return Double.MAX_VALUE;
        }
        
        // If not visited, then weight it more
        if(!visited_locations.contains(next) && game.amountOfExits(next) > 1) {
            //System.out.println(m +"A: "+ game.amountOfExits(next)));
            return ((game.ROWS + game.COLUMNS) - next.blockDistance(game.getFinish().getPosition()))*2 + game.amountOfExits(next) + 10;
        } else if(visited_locations.indexOf(next) < visited_locations.indexOf(player.getPosition()) && game.amountOfExits(next) > 1) {
            //System.out.println(m +"B: "+(next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next))+5);
            return ((game.ROWS + game.COLUMNS) - next.blockDistance(game.getFinish().getPosition()))*2 + game.amountOfExits(next) + 5;
        } else {
            //System.out.println(m +"C: "+(next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next)));
            return ((game.ROWS + game.COLUMNS) - next.blockDistance(game.getFinish().getPosition()))*2 + game.amountOfExits(next);
        }
    }
}

