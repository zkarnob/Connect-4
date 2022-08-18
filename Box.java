
package connectfour;



public class Box{
	
	//Variables and collections
	private boolean empty;
	private int num;
	private Disc theDisc;

  
	
        
        
	//Constructors
	public Box(int number) {
		setNum(number);
		setEmpty(true);
	}//end Constructor
	
	//Getters and setters
	public int getNum() {
		return this.num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public boolean getEmpty() {
		return this.empty;
	}
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}
	//end getters and setters
	
	//addDisc method
        
	public void addDisc(Disc theDisc) {
         
		this.theDisc = theDisc;
		setEmpty(false);
	}//end addDisc
	
	//returnString method

    /**
     *
     * @return
     */
        @Override
	public String toString () {
		String output;
		if (this.empty == true) {
			output = "  (__________) ";
		} else {
			output = this.theDisc.getColour();
		}
		return output;
	}// end returnString
	
}