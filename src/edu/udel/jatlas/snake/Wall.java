package edu.udel.jatlas.snake;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.udel.jatlas.gameframework.Position;

/**
 * A simple straight-line wall that must be at a 90 degree
 * angle.  Also start must be to the left of end OR it must
 * be above end.
 * 
 * Note: I removed the old code because inner classes were
 *  too confusing for now.
 *  
 * @author jatlas
 *
 */
public class Wall implements Iterable<Position> {
    private Position start;
    private Position end;
    private Set<Position> allPositions;
    
    public Wall(Position start, Position end) {
        this.allPositions = new HashSet<Position>();
        if (start.getRow() == end.getRow()) {
            // horizontal wall
            int r = start.getRow();
            int end_c = end.getColumn();
            for (int c = start.getColumn(); c <= end_c; c++) {
                allPositions.add(new Position(c, r));
            }
        }
        else {
            // vertical wall
            int c = start.getColumn();
            int end_r = end.getRow();
            for (int r = start.getRow(); r <= end_r; r++) {
                allPositions.add(new Position(c, r));
            }
        }
        this.start = start;
        this.end = end;
    }
    
    /**
     * Returns true if the wall contains the position.
     * 
     * We are assuming that all Walls are 90 degree straight lines
     * (no diagonal walls).
     * 
     * @param p
     * @return
     */
    public boolean contains(Position p) {
        return allPositions.contains(p);
    }

    public String toString() {
        return "Wall [start=" + start + ", end=" + end + "]";
    }

    public Iterator<Position> iterator() {
        return allPositions.iterator();
    }
}
