package edu.udel.jatlas.gameframework;


/**
 * Right now this class does not have much code in it, but
 * we will add some later when we move to Android.
 * 
 * @author jatlas
 *
 * @param <G>
 */
public abstract class Game {
    /**
     * @return true if the Game is over
     */
    public abstract boolean isEnd();
    
    /**
     * It is expected that the Game return a status
     * or String representation of its current state
     */
    public abstract String toString();
    
    /**
     * Performs an action on the game.
     * 
     * @param action
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void perform(Action action) {
        // in the future we will add some code to this method
        action.update(this);
    }
}
