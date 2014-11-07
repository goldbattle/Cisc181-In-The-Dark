package edu.udel.jatlas.gameframework;

import java.util.ArrayList;
import java.util.List;


/**
 * This class now has some code to handle a basic listener
 * pattern. Game models update typically with two kinds of events:
 * actions that are performed on the game by a player, and
 * time based tick updates. Both of these updates generate a call
 * to methods on each of the GameListener interfaces that are
 * listening to events on this game.
 * 
 * A typical way to "run" a game would be to:
 * 
 * SomeGame g = new SomeGame();
 * g.addGameListener(new SomeGameListener());
 * g.start();
 * 
 * See specific examples in the main method of subclasses of Game 
 * for the sample project checkpoints for SnakeGame and TicTacToe5x5Game.
 * 
 * @author jatlas
 */
// generics are nice but cause a lot of extra confusing code, so I
// have made the choice to use them sparingly, so I need to suppress
// the type warnings so you don't see a lot of yellow lines in Eclipse.
@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class Game {
    private List<GameListener> listeners;
    
    // a counter that stores the current tick (starts at 0)
    private long tickId;
    // stores the last time an action was performed
    private long lastActionTime;
    // stores the last time an onTick was processed
    private long lastTickTime;
    // if the game is Tickable this will have a Ticker, otherwise null
    private Ticker ticker;
    
    /**
     * Allows subclasses to not have to call a specific Game constructor.
     * Creates an empty List to store listeners in.
     */
    protected Game() {
        listeners = new ArrayList<GameListener>();
        tickId = 0;
        lastActionTime = 0;
        lastTickTime = 0;
    }
    
    /**
     * Adds the given listener to this game.  Callbacks will be made
     * to the event methods on the listener from this game.
     * 
     * @param listener
     */
    public void addGameListener(GameListener listener) {
        listeners.add(listener);
    }

    /**
     * Removes the given listener from this game.

     * @param listener
     * @return true if the given listener was actually listening
     *   to this game, false otherwise
     */
    public boolean removeGameListener(GameListener listener) {
        return listeners.remove(listener);
    }
    
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
    public final void perform(Action action) {
        if (action.isValid(this)) {
            onPerformAction(action);
            
            lastActionTime = System.currentTimeMillis();
            for (GameListener listener : listeners) {
                listener.onPerformActionEvent(action, this);
            }
            
            if (isEnd()) {
                end();
            }
        }
    }
    
    /**
     * Marked as final to prevent overriding.  Subclasses must
     * put time-based logic in their onTick method.  This ensures
     * that listeners are notified after every tick.
     */
    public final boolean tick() {
        onTick();
        lastActionTime = System.currentTimeMillis();
        for (GameListener listener : listeners) {
            listener.onTickEvent(this);
        }
        tickId++;
        
        if (isEnd()) {
            end();
        }
        
        return true;
    }
    
    /**
     * Marked as final to prevent overriding.  Subclasses must
     * put start logic in their onStart method.  This ensures
     * that listeners are notified of the start and that
     * time-based games start the Ticker.
     */
    public final void start() {
        if (this instanceof Tickable) {
            ticker = Ticker.start((Tickable)this);
        }
        
        onStart();
        for (GameListener listener : listeners) {
            listener.onStartEvent(this);
        }
    }

    /**
     * Marked as final to prevent overriding.  This is only
     * called internally from the tick and perform methods when
     * isEnd produces a true value.  If you want to programmatically
     * end a game, then you should have a way of setting a boolean
     * property of your game such that your isEnd will produce a
     * true value.
     */
    private final void end() {
        if (ticker != null) {
            ticker.end();
        }
        
        onEnd();
        for (GameListener listener : listeners) {
            listener.onEndEvent(this);
        }
    }
    

    /**
     * This is the method to override if you need to do something
     * different than the default update for the Action.
     */
    protected void onPerformAction(Action action) {
        action.update(this);
    }
    
    /**
     * This is the method to override for game specific start
     * logic.
     */
    protected void onStart() {
    }
    
    /**
     * This is the method to override for game specific end
     * logic.
     */
    protected void onEnd() {
    }
    
    /**
     * This is the method to override for game specific time-based
     * logic.
     */
    protected void onTick() {
    }
    
    /**
     * Broadcasts a custom event String to listeners.
     * 
     * @param event
     */
    public void broadcastEvent(String event) {
        for (GameListener listener : listeners) {
            listener.onEvent(event, this);
        }
    }
    
    public long getTickId() {
        return tickId;
    }
    
    public long getLastActionTime() {
        return lastActionTime;
    }
    
    public long getLastTickTime() {
        return lastTickTime;
    }
}
