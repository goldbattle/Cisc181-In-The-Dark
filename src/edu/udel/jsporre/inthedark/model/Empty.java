package edu.udel.jsporre.inthedark.model;

public class Empty implements IGameTile{
    
    @Override
    public boolean canWalkOn() {
	return false;
    }

    @Override
    public void updateTick() {
	// Do nothing!
    }
    
    public String toString() {
	return "";
    }
}
