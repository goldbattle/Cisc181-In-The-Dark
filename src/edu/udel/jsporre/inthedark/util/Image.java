package edu.udel.jsporre.inthedark.util;

public class Image {

    public char temp;

    /**
     * Temp constructor for the image class
     * This just holds a char that represents that object
     * @param temp
     */
    public Image(char temp) {
	this.temp = temp;
    }

    public String toString() {
	return temp + "";
    }
}
