
package connectfour;


import java.util.ArrayList;
import javax.swing.JOptionPane;
public class Matrix{
	
	//Variables and collections
	final int ROWS = 6; //Grid rows
	//delete private int number;
	ArrayList <Row> theGrid = new ArrayList <Row> ();
	
        
        
	//Constructor
	public Matrix() {
		Row tempRow;
		for (int i = ROWS;i>=1;i--) {
			tempRow = new Row(i);
			theGrid.add(tempRow);
		}// end for i
	}//end constructor
	

	
}
