package edu.udel.jsporre.inthedark.util;

import edu.udel.jsporre.inthedark.game.MazeGame;

public class CountDown {

    private int total_sec;
    private boolean count_dir;

    public CountDown(int minutes, int seconds) {
	if(minutes == -1 || seconds == -1) {
	    count_dir = false;
	    total_sec = 0;
	} else {
	    count_dir = true;
	    total_sec = minutes*60 + seconds;
	    total_sec *= 1000;
	}

    }

    public void onTick(MazeGame game) {
	if(count_dir)
	    total_sec -= game.getRealTimeTickLength();
	else
	    total_sec += game.getRealTimeTickLength();
    }

    public String get_remaining() {
	if((total_sec/1000)%60 < 10)
	    return (int)((total_sec/1000)/60)+":0"+(int)(total_sec/1000)%60+"s";
	return (int)((total_sec/1000)/60)+":"+(int)(total_sec/1000)%60+"s";
    }
    
    public boolean isTimeEnd() {
	if(count_dir && total_sec/1000 < 0)
	    return true;
	return false;
    }
}
