package edu.udel.jsporre.inthedark.game;

import edu.udel.jsporre.inthedark.model.IGameTile;
import edu.udel.jsporre.inthedark.util.Position;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.view.View;

public class MazeGameView extends View {

    MazeGameActivity activity;
    private Bitmap wallImage;
    private Bitmap playerImage;
    private Bitmap finishImage;
    private RectF rectF = new RectF();

    public MazeGameView(MazeGameActivity context) {
	super(context);
	activity = context;
	setBackgroundColor(Color.BLACK);
	// Images
	wallImage = loadImage("wall");
	playerImage = loadImage("player");
	finishImage = loadImage("finish");
	// Others
	setFocusable(true);
	setFocusableInTouchMode(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
	super.onDraw(canvas);
	
	// Scale cords
	canvas.save();
	canvas.scale(getWidth() / (float)MazeGame.ROWS, getHeight() / (float)MazeGame.COLUMNS);
	
	// Debug Text
//	Paint gridPaint = new Paint();
//	gridPaint.setColor(Color.WHITE);
//	gridPaint.setStyle(Style.FILL_AND_STROKE); 
//	gridPaint.setTextSize(30);
//	int x = 100, y = 100;
//	for(String line: activity.game.toString().split("\n")){
//	    canvas.drawText(line, x, y, gridPaint);
//	    y+=30;
//	}
	
	for (IGameTile wall : activity.getCurrentGame().getTiles()) {
	    Position pos = wall.getPosition();
	    setRectFromPosition(pos);
	    canvas.drawBitmap(wallImage, null, rectF, null);
	}
	
	// Players
	Position pos1 = activity.getCurrentGame().getPlayer().getPosition();
	setRectFromPosition(pos1);
	canvas.drawBitmap(playerImage, null, rectF, null);
	
	// Finish
	Position pos2 = activity.getCurrentGame().getFinish().getPosition();
	setRectFromPosition(pos2);
	canvas.drawBitmap(finishImage, null, rectF, null);
	
	// Restore the cordinate system
	canvas.restore();
    }

    /**
     * Helper methods so we don't have to write this code as much
     */
    private Bitmap loadImage(String name) {
	return BitmapFactory.decodeResource(activity.getResources(), 
		activity.getResources().getIdentifier(name, "drawable", getClass().getPackage().getName()));
    }

    private void setRectFromPosition(Position position) {
	rectF.set(position.getColumn(), position.getRow(), position.getColumn()+1f, position.getRow()+1f);
    }


}
