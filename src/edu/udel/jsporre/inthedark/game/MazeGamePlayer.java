package edu.udel.jsporre.inthedark.game;

import edu.udel.jsporre.inthedark.model.PlayerDirection;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

public class MazeGamePlayer implements View.OnTouchListener, View.OnKeyListener {
    private MazeGameActivity activity;

    public MazeGamePlayer(MazeGameActivity activity){
	this.activity = activity;
    }

    public boolean onTouch(View v, MotionEvent event){
	int action = event.getAction();
	MazeGame game = activity.getCurrentGame();

	if (game != null){
	    /*	if(game.isEnd()){
				ac
			}*/
	    if(action == MotionEvent.ACTION_DOWN){
		//where did they click on the board?
		int row = (int)((event.getY() / v.getHeight()) * game.ROWS);
		int col = (int)((event.getX() / v.getWidth()) * game.COLUMNS);

		int changeX = col - game.getPlayer().getPosition().getColumn();
		int changeY = row - game.getPlayer().getPosition().getRow();
		//if the event is farther horizontally than vertically, consider it a right left move
		if ((Math.abs(changeX) > Math.abs(changeY))){
		    if (changeX > 0){
			game.perform(new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT));
		    }
		    else if(changeX < 0){
			game.perform(new ActionPlayMove(PlayerDirection.DIRECTION_LEFT));
		    }
		}
		//if the event is farther vertically than horizontally, consider it an up down move

		if ((Math.abs(changeX) < Math.abs(changeY))){
		    if (changeY > 0){
			game.perform(new ActionPlayMove(PlayerDirection.DIRECTION_DOWN));
		    }
		    else if(changeY < 0){
			game.perform(new ActionPlayMove(PlayerDirection.DIRECTION_UP));
		    }
		}

	    }
	}

	return false;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_UP) {
            activity.getCurrentGame().perform(new ActionPlayMove(PlayerDirection.DIRECTION_UP));
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_DOWN) {
            activity.getCurrentGame().perform(new ActionPlayMove(PlayerDirection.DIRECTION_DOWN));
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_LEFT) {
            activity.getCurrentGame().perform(new ActionPlayMove(PlayerDirection.DIRECTION_LEFT));
        }
        else if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_DPAD_RIGHT) {
            activity.getCurrentGame().perform(new ActionPlayMove(PlayerDirection.DIRECTION_RIGHT));
        }        
        return false;
    }

}
