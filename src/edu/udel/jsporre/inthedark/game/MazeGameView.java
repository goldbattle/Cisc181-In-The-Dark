package edu.udel.jsporre.inthedark.game;

import android.graphics.Color;
import android.view.View;

public class MazeGameView extends View {
    
    MazeGameActivity activity;
    
    public MazeGameView(MazeGameActivity context) {
        super(context);
        activity = context;
        setBackgroundColor(Color.BLACK);
        setFocusable(true);
        setFocusableInTouchMode(true);
    }
    
    

}
