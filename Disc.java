
package connectfour;

//import java.awt.Graphics;
public class Disc{
	
	//Variables and collections
	private String mark;
	
	//Constructors
	public Disc(){}
	public Disc(String mark) {
		setColour(mark);
	}
       
	
	
	//Getters and setters
	public String getColour () {
		return this.mark;
	}
	public void setColour(String mark) {
		this.mark = mark;
	}
	//end getters and setters
}
