
package connectfour;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Row {
	
	//Variables and collections
	final int COLS = 7; //Grid columns
	private int number;
	ArrayList <Box> theRow = new ArrayList <Box> ();
	
	//Constructor
	public Row(int number) {
		Box tempSquare;
		setNumber(number);
		for (int loop = 1; loop <= COLS;loop++) {
			tempSquare = new Box(loop);
			this.theRow.add(tempSquare);	
		}//end for
	}//end Row
	
		
	//Getters and Setters
	public int getNumber () {
		return this.number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	//end getters and setters
	
}