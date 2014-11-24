package edu.udel.jatlas.gameframework;


/**
 * A base implementation of a GameTicker.
 * The only way to use this class is to create an
 * instance of one of the subclasses.
 * This is abstract because there is some platform
 * specific behavior needed (Java uses Threads
 * but Android has its own Handlers).
 *  
 * @author jatlas
 */
public abstract class GameTicker  {
    protected Game game;
    
    // all false initially
    protected boolean started;
    protected boolean ended;
    protected boolean queued;
    
    protected GameTicker() {
    }
    
    protected abstract void nextTask();
        
    protected void tick() {
        queued = false;
        if (!ended) {
            boolean queueNext = game.tick();
            if (game.isEnd()) {
                end();
            }
            else if (queueNext && !queued) {
                nextTask();
            }
        }
        else {
            end();
        }
    }
    
    // the following methods are protected, they should only be called
    // from the Game class
    
    protected void start(Game game) {
        if (!started) {
            this.game = game;
            started = true;
            nextTask();
        }
    }
    
    protected void end() {
        ended = true;
    }

    protected void restart() {
        if (!ended && !queued) {
            nextTask();
        }
    }
}
