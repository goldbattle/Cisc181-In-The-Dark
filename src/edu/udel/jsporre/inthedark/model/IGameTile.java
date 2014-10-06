package edu.udel.jsporre.inthedark.model;

public interface IGameTile {
  
  /**
   * This method asks if the current tile can be walked on by a player
   * 
   * @return Boolean true if it can be walked on
   */
  public boolean canWalkOn();
  
  /**
   * This method allows the tile to get game ticks and update its
   * settings over time
   */
  public void updateTick();
  
  
}
