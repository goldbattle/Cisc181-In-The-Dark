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
        super(game, "Maze AI");
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

    @Override
    public double getHeuristicScore(Action<MazeGame> action, MazeGame game) {
        if (game.isEnd()) {
            return -1;
        }
        // Convert the action to our ActionPlayMove
        ActionPlayMove m = (ActionPlayMove)action;
        // Add the current position
        if(!visited_locations.contains(game.getPlayer().getPosition())) {
            visited_locations.add(game.getPlayer().getPosition());
        }
        System.out.print(visited_locations);
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
        // Don't go back to the start
        if(next.equals(game.getFinish().getPosition())){
            return 0;
        }
       
        if(!visited_locations.contains(next)) {
            System.out.println(m +""+(next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next)-5));
            return (game.ROWS + game.COLUMNS) - next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next) - 5;
        } else {
            System.out.println(m +""+(next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next)));
            return (game.ROWS + game.COLUMNS) - next.blockDistance(game.getFinish().getPosition()) + game.amountOfExits(next);
        }
    }
}
