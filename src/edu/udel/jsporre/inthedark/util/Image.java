package edu.udel.jsporre.inthedark.util;

public class Image {

    // TODO: Store the icon of the tile here
    public String temp;

    /**
     * Temp constructor for the image class This just holds a char that
     * represents that object
     * 
     * @param temp
     */
    public Image(String temp) {
	this.temp = temp;
    }
    
    // TODO: Handle returning an image icon
    
    /**
     * Prints out the string representation of the image object
     */
    public String toString() {
	return temp;
    }
}
