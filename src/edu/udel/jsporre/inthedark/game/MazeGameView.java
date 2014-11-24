package edu.udel.jsporre.inthedark.game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
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
    
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        
        Paint gridPaint = new Paint();
        gridPaint.setColor(Color.WHITE);
        gridPaint.setStyle(Style.FILL_AND_STROKE); 
        gridPaint.setTextSize(30);
        
        int x = 100, y = 100;
        for(String line: activity.game.toString().split("\n")){
              canvas.drawText(line, x, y, gridPaint);
              y+=30;
        }
        
    }
    

}
