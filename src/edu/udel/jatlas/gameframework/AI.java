package edu.udel.jatlas.gameframework;

import java.util.ArrayList;
import java.util.List;

public abstract class AI<G extends Game> implements GameListener<G> {
    private String identifier;
    
    public AI(String identifier) {
        this.identifier = identifier;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public String toString() {
        return getIdentifier();
    }
    
    public void onEndEvent(G game) {
    }
    
    /**
     * Subclass may override this, but AI does not need to
     * handle this event
     */
    public void onEvent(String event, G game) {
    }
    
    /**
     * 1. Check if it is "my" turn (always the case in a single player game)
     * 2. Perform the best action
     */
    protected void takeTurnIfMyTurn(G game) {
        if (isMyTurn(game)) {
            performBestAction(game);
        }
    }

    public void onStartEvent(G game) {
    }

    public void onPerformActionEvent(Action<G> action, G game) {
    }
    
    /**
     * Subclasses should override if they are multi-player games.
     */
    protected boolean isMyTurn(G game) {
        // for one player games this is fine, it is always our turn
        return true;
    }
    
    /**
     * The AI will try to find its best action
     * to perform this tick and perform that action on the game.
     */
    public void onTickEvent(G game) {
        takeTurnIfMyTurn(game);
    }
    
    protected void performBestAction(G game) {
        Action<G> action = getBestAction(game);
        // in a turn based game the AI might have no best action (since
        //  no actions would be valid when it is not its turn)
        if (action != null) {
            game.perform(action);
        }
    }

    /**
     * You will complete this method as part of DL2.
     * 
     * @param game
     * @return
     */
    public Action<G> getBestAction(G game) {        
        List<Action<G>> validActions = getAllValidActions(game);
        List<Action<G>> possibleActions = new ArrayList<Action<G>>();
        
        double max_score = Double.NEGATIVE_INFINITY;
        for (Action<G> action : validActions) {
            double score = getHeuristicScore(action, game);
            if (score > max_score) {
                max_score = score;
                possibleActions.clear();
            }
            if (score >= max_score) {
                possibleActions.add(action);
            }
        }
        
        if (possibleActions.isEmpty()) {
            return null;
        }
        else {
            return possibleActions.get((int)(Math.random()*possibleActions.size()));
        }
    }
    
    /**
     * Get all possible valid actions for the game.
     * 
     * @param game
     * @return
     */
    public abstract List<Action<G>> getAllValidActions(G game);

    /**
     * AI subclasses need to implement a heuristic that gives a larger
     * number for "better" actions given the game state.
     */
    public abstract double getHeuristicScore(Action<G> action, G game);
}
