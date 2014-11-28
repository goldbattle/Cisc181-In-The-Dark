package edu.udel.jsporre.inthedark.game;

import edu.udel.jatlas.gameframework.Action;
import edu.udel.jatlas.gameframework.Game;
import edu.udel.jatlas.gameframework.GameListener;
import edu.udel.jatlas.gameframework.android.AndroidTicker;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MazeGameActivity extends Activity implements GameListener<MazeGame> {

    TextView status;
    TextView timer;
    MazeGameView gameView;
    MazeGame game;
    
    public static final int GAMETYPE_AI = 0;
    public static final int GAMETYPE_HUMAN = 1;
    public static final int GAMETYPE_SAND = 2;
    
    private int gameType;

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
	// Make sure we are not yet created
	if (game != null && game.getLifecycle() != Game.ENDED) {
            game.end();
        }
	// Create based on type
	if(gameType == GAMETYPE_HUMAN) {
	    game = new MazeGame(1);
	    MazeGamePlayer human = new MazeGamePlayer(this);
	    gameView.setOnTouchListener(human);
	}
	else if(gameType == GAMETYPE_SAND){
	    game = new MazeGame(-1);
	    MazeGamePlayer human = new MazeGamePlayer(this);
	    gameView.setOnTouchListener(human);
	}
	else if(gameType == GAMETYPE_AI) {
	    game = new MazeGame(-1);
	    game.addGameListener(new MazeAI(game));
	}
	
	game.addGameListener(this);
	game.start(new AndroidTicker());
	//game.createDefaultGame();
	//game.addGameListener(new ConsoleListener());
	game.perform(new ActionCreateMaze(game.ROWS, game.COLUMNS));
    }

    public void updateViews() {
	status.setText("Score: "+game.getStatus());
	timer.setText(game.getTimer());
	
	status.invalidate();
	timer.invalidate();
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
	if (event.equals("end_of_game")) {
	    Toast.makeText(this, "You got a score of: "+game.getStatus(), Toast.LENGTH_LONG).show();
        }
	updateViews();
    }
    
    public MazeGame getCurrentGame(){
	return game;
    }
    
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("AI Run");
        menu.add("Human Countdown");
        menu.add("Human Sandbox");
        menu.add("Exit");
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("AI Run")) {
            gameType = GAMETYPE_AI;
            startGame();
        }
        else if (item.getTitle().equals("Human Countdown")) {
            gameType = GAMETYPE_HUMAN;
            startGame();
        }
        else if (item.getTitle().equals("Human Sandbox")) {
            gameType = GAMETYPE_SAND;
            startGame();
        }
        else if (item.getTitle().equals("Exit")) {
            finish();
        }
        gameView.invalidate();
        return true;
    }
}
