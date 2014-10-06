package edu.udel.jsporre.inthedark.util;

/**
 * An abstraction of a component in 3 dimensions.
 * 
 * @author jatlas
 */
public abstract class Component3d {
    private double x;
    private double y;
    private double z;

    protected Component3d(double x, double y) {
	this(x, y, 0);
    }

    protected Component3d(double x, double y, double z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    /**
     * You can safely ignore this method. It allows Components to be
     * used in specific Java Collections.
     * 
     * This code was auto-generated by Eclipse
     */
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(x);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(y);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	temp = Double.doubleToLongBits(z);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	return result;
    }

    /**
     * Any Component3d is equivalent to another Component3d
     * if they are both the same class (which could be a subclass
     * of Component3d) and they have the same x, y, and z values.
     * 
     * This code was auto-generated by Eclipse
     */
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Component3d other = (Component3d) obj;
	if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
	    return false;
	if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
	    return false;
	if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
	    return false;
	return true;
    }   

    public double getX() {
	return x;
    }

    public double getY() {
	return y;
    }

    public double getZ() {
	return z;
    }

    public void setX(double x) {
	this.x = x;
    }

    public void setY(double y) {
	this.y = y;
    }

    public void setZ(double z) {
	this.z = z;
    }

    public String toString() {
	return getClass().getSimpleName() + " [x=" + x + ", y=" + y + ", z=" + z + "]";
    }    
}
