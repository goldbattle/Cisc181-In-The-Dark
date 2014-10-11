package edu.udel.jsporre.inthedark.util;

/**
 * A class representing a Position with up to 3 dimensions.
 * Supports 2 dimensions by using value 0 for the 3rd dimension.
 * Also supports integer based coordinates by providing wrapper
 * methods for getters/setters on row, column, and depth.
 * 
 * @author jatlas
 *
 */
public class Position extends Component3d {
    public Position(double x, double y) {
	super(x, y);
    }

    /**
     * @return the column as the containing grid column 
     *  (i.e. the x value as an int).
     */
    public int getColumn() {
	return (int)getX();
    }

    /**
     * @return the row as the containing grid row 
     *  (i.e. the y value as an int).
     */
    public int getRow() {
	return (int)getY();
    }


    public void setColumn(int column) {
	setX(column);
    }

    public void setRow(int row) {
	setY(row);
    }
    
    public void addColumn(int column) {
	setX(getX() + column);
    }
    
    public void addRow(int row) {
	setY(getY() + row);
    }
    /**
     * Computes the distance "as the crow flies" between two Positions
     * in a Cartesian coordinate system.
     * 
     * @param other
     * @return
     */
    public double distance(Position other) {
	double dx = other.getX() - getX();
	double dy = other.getY() - getY();
	return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Computes the distance in grid spaces between two Positions as
     * would be required to "walk" along city blocks. This is also
     * known in game design literature as Manhattan block distance.
     * 
     * Because this uses the int accessor methods, if we have:
     * 
     * Position a = new Position(3.999, 2.0);
     * Position b = new Position(4.0, 1.999);
     * 
     * a.blockDistance(b) -> 2
     * 
     * whereas a.distance(b) -> 0.0014142
     * 
     * @param other
     * @return
     */
    public int blockDistance(Position other) {
	int dx = Math.abs(other.getColumn() - getColumn());
	int dy = Math.abs(other.getRow() - getRow());
	return dx + dy;
    }
}
