package edu.udel.jsporre.inthedark.game;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.GameListener;
import edu.udel.jatlas.gameframework.android.AndroidTicker;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MazeGameActivity extends Activity implements GameListener<MazeGame> {

    TextView status;
    TextView timer;
    MazeGameView gameView;
    MazeGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = new TextView(this);
        status.setTypeface(Typeface.MONOSPACE);
        timer = new TextView(this);
        timer.setTypeface(Typeface.MONOSPACE);
        //gameView= new TicTacToe5x5View2D(this);
        gameView = new MazeGameView(this);
        status.setTypeface(Typeface.MONOSPACE);

        
        
        // So much crap to make this stay on the right
        RelativeLayout relativeLayout = new RelativeLayout(this);
        RelativeLayout.LayoutParams relativeLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        relativeLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        relativeLayout.addView(timer, relativeLayoutParams);
        
        
        // Nice layout, status
        LinearLayout ll0 = new LinearLayout(this);
        ll0.addView(status);
        ll0.addView(relativeLayout);
        ll0.setOrientation(LinearLayout.HORIZONTAL);
        
        // Layout for game
        LinearLayout ll1 = new LinearLayout(this);
        ll1.addView(ll0);
        ll1.addView(gameView);
        ll1.setOrientation(LinearLayout.VERTICAL);
        
        // Start the game, and display
        startGame();
        setContentView(ll1);
    }

    private void startGame() {
        game = new MazeGame();
        
        game.addGameListener(this);
        game.addGameListener(new MazeAI(game));
        game.start(new AndroidTicker());
        //game.createDefaultGame();
        //game.addGameListener(new ConsoleListener());
        game.perform(new ActionCreateMaze(game.ROWS, game.COLUMNS));
    }
    
    public void updateViews() {
        status.setText(game.getStatus());
        timer.setText(game.getTimer());
        gameView.invalidate();
    }

    @Override
    public void onPerformActionEvent(Action<MazeGame> action, MazeGame game) {
        updateViews();
    }
    
    @Override
    public void onStartEvent(MazeGame game) {
        updateViews();
    }

    @Override
    public void onEndEvent(MazeGame game) {
        updateViews();
    }
    
    @Override
    public void onTickEvent(MazeGame game) {
        updateViews();
    }
    
    @Override
    public void onEvent(String event, MazeGame game) {
        updateViews();
    }
     public MazeGame getCurrentGame(){
    	 return game;
     }
}
