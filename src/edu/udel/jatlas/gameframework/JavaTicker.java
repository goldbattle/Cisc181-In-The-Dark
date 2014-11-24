package edu.udel.jatlas.gameframework;

import java.util.Timer;
import java.util.TimerTask;

public class JavaTicker extends GameTicker {
    private Timer timer;
    protected class JavaTickerTask extends TimerTask {
        public void run() {
            tick();
        }
    }
    
    public JavaTicker() {
        timer = new Timer();
    }
    
    protected void nextTask() {
        timer.schedule(new JavaTickerTask(), game.getRealTimeTickLength());
    }
    
    protected void end() {
        super.end();
        timer.cancel();
    }
}
