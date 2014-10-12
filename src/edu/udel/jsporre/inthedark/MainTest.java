package edu.udel.jsporre.inthedark;

import junit.framework.TestCase;
import edu.udel.jsporre.inthedark.util.Position;

public class MainTest extends TestCase {
    
    public void test_position() {
	Position temp1 = new Position(0,0);
	Position temp2 = new Position(0,0);
	Position temp3 = new Position(0,1);
	Position temp4 = new Position(1,0);
	
	// Asserts
	assertTrue(temp1.equals(temp2));
	assertFalse(temp1.equals(temp3));
	assertFalse(temp1.equals(temp4));
    }
}
