
package connectfour;

import javax.swing.JOptionPane;
public class GamePlay {
	
	//Declare constants
	   final int ROWS = 6;
	  final int colums = 7;
	  final int  winscore = 4;
	  final int filled = ROWS * colums;
        
    public    String p1=JOptionPane.showInputDialog(null,"enter 1st your name::","Player 1");
     
             public    String p2=JOptionPane.showInputDialog(null,"enter 2nd your name::","player2");
     
     
     
     
	//Declare private variables
        
	public  Disc player1, player2, tempDisc;
	 public Matrix game;
	
	//GamePlay method to run only for a New Game
	public GamePlay () {
		
		
				
		//Create the player objects (no colour yet)
		player1 = new Disc();
		player2 = new Disc();
		game = new Matrix();	

		//Ask player1 to pick a colour	
		int colourSelection = playerColour(1);
		//Assign colours as per player1's choice
		assignColour(player1, player2, colourSelection);

		//Create a new game and give player1 a disc
		
		tempDisc = player1;
	}

		

	public void startGame() {
				
		//Declare variables
		int input = 0;
		int row = 0;		
			
		//Gameplay
		do {
			String output = displayGrid(game, player1, player2, tempDisc);
			String title = "Welcome to the Connect Four game";
			String []options = {" EXIT", "          1             " , "2", "3", "4", "5", "6", "7"};
			int reply = JOptionPane.showOptionDialog(null, output, title, JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
			input = reply;
			if (input == 0){
				JOptionPane.showMessageDialog(null, "Thank you for playing, see you soon!");
				break;				
			}//end if
	
			//check if the column is full
			if (checkColumnOverload(row)){ 
				tempDisc = changePlayer(player1, player2, tempDisc); //change player
			}
			//add disc to selected square
			row = addDiscToSquare(game, tempDisc, input);
			//change player
			tempDisc = changePlayer(player1, player2, tempDisc);
			//display the grid
			
			
				
		} while (!checkHorizontally(game) && !checkVertically(game, input)  && !checkDiagonally(game, input, row)&& !checkIfGridIsFull(game));
		
		//Conditions to show relevant message according to the result
		if (checkHorizontally(game)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR IN ROW " + row);
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
		}//end if
		else if(checkVertically(game, input)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR VERTICALLY IN COLUMN " + input);
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
		}//end else if
		else if(checkDiagonally(game, input, row)){
			JOptionPane.showMessageDialog(null, "YOU WIN! \nYOU CONNECTED FOUR DIAGONALLY");
			showWinner(player1, player2, tempDisc);
			displayGrid2(game); //display the winning grid
                       
		}//end else if
               
	}

	//Method for Player 1 to select his colour [returns 0-(RED) or 1-(YELLOW)]
	public   int playerColour(int playerNumber) {
		String []options = {"RED", "YELLOW"};
		String message = p1 + ", please select your colour.";
		int reply = JOptionPane.showOptionDialog(null, message, "Colour selection", JOptionPane.DEFAULT_OPTION, 
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                
		return reply;
	}//end playerColour

	//Method to assignColour to players
	public   void assignColour(Disc player1, Disc player2, int colourSelection) {
		if (colourSelection == 0) {
			player1.setColour("  |____ (R)_____|");
			player2.setColour("  |____ (Y)_____|");
			JOptionPane.showMessageDialog(null, p1+"  takes the RED colour.\n"+p2+"    takes the YELLOW colour");
		}//end if
		else {
			player1.setColour("  |____ (Y)_____|");
			player2.setColour("  |____ (R)_____|");
			JOptionPane.showMessageDialog(null,p1+"  takes the YELLOW colour.\n"+p2+"  takes the RED colour");
		}//end else
	}//end assignColour method

	//Method to access and print grid in OptionPane
	public   String displayGrid(Matrix grid, Disc player1, Disc player2, Disc tempDisc) {
		String output;
                
		if (tempDisc == player1 ){
			output = p1+", it's your turn.\n\n";
		}
		else {
			output = p2+", it's your turn.\n\n";
		}
		for ( Row tempRow : grid.theGrid) {
			String rowOutput = "";  //the output of each row
			for (Box tempSquare : tempRow.theRow)
                        {
				rowOutput = rowOutput +  "            " + tempSquare.toString();
			}//end inner for loop
			output = output + rowOutput + "\n" + "\n";
                       ;
		}//end outer for loop
		return output;
	}//end showGridinConsole method

	//Method to display the winning grid
	public   String displayGrid2(Matrix grid) {
		String output = "GAME OVER\n\n";
		for ( Row tempRow : grid.theGrid) {
			String rowOutput = "";  //the output of each row
			for (Box tempSquare : tempRow.theRow) {
				rowOutput = rowOutput + "         " + tempSquare.toString();
			}//end inner for loop
			output = output + rowOutput + "\n" + "\n";
		}//end outer for loop
		JOptionPane.showMessageDialog(null, output);
		return output;
	}//end showGridinConsole method
        

	//Access the grid and add player's disc to desired column
	public   int addDiscToSquare (Matrix grid, Disc theDisc, int colNum) {
		int rowNum = 0;
		for (int row = 1;row<=ROWS;row++){			
			for ( Row tempRow : grid.theGrid) {
				for (Box tempSquare : tempRow.theRow) {
                                   
                                    
					if (tempSquare.getNum() == colNum && tempRow.getNumber() == row && tempSquare.getEmpty()) {
                                           
						tempSquare.addDisc(theDisc);
						rowNum = row;
                                                
						return rowNum;
					}//end if 
				}//end inner for loop
			}//end outer for loop3		
		}// end row iteration loop
		return rowNum;
	}//end addDiscToSquare method

	//check Column method to check if the column has already 6 discs
	public   boolean checkColumnOverload (int row) {
		if (row == ROWS) {
			String output = "This column is full. Please select another column";
		 	JOptionPane.showMessageDialog(null, output);
			return true;
		}//end if
		return false;
	}//end checkColumn method

	//method to check connect four in a row
	public   boolean checkHorizontally (Matrix grid) {		
		boolean fourInARow = false;
		for ( Row tempRow : grid.theGrid) {
			String item = "";
			int score = 1;
			for (Box tempSquare : tempRow.theRow) {
				if (tempSquare.toString() == item && !tempSquare.getEmpty()) {
                                
					score++;
				} 
				else if (tempSquare.toString() != item || tempSquare.getEmpty()){
					score = 1;
				}
				item = tempSquare.toString();
				if (score ==  winscore) {
					fourInARow = true;
				}//end if
			}//End loop through squares
		}//end loop through rows
		return fourInARow;
	}//end checkRow

	//method to check connect four in a column
	public   boolean checkVertically(Matrix grid, int colNum) {
		boolean fourInACol = false;
		int score = 0;
		String item = "";  //temporary variable to hold the tempSquare
		for (int row = 1;row<=ROWS;row++){	
			for ( Row tempRow : grid.theGrid) {
				for (Box tempSquare : tempRow.theRow) {
					if (tempSquare.getNum() == colNum && tempRow.getNumber() == row && !tempSquare.getEmpty()) {
					                        
                                                                                                                 if (tempSquare.toString() == item) {
                                                                                                                                   
							score++;
						}//end if
						else  {
							score = 1;
						}//end else
						item = tempSquare.toString(); //change to the next tempSquare
					}//end if					
				}//end inner for loop				
			}//end outer for loop
		};
		if (score ==  winscore) {
			fourInACol = true;
		}//end if
		return fourInACol;
	}//end checkVertically

	//method to check four diagonally	
	public   boolean checkDiagonally (Matrix grid, int colNum, int rowNum) {
		Boolean fourInaDiagonal = false;
		String item = "";  //variable to keep the played colour
		for ( Row tempRow : grid.theGrid) {
			for (Box tempSquare : tempRow.theRow) {
				if (tempSquare.getNum() == colNum && tempRow.getNumber() == rowNum && !tempSquare.getEmpty()) {
					item = tempSquare.toString(); //assign to item the played colour
				}//end if 
			}//end inner for loop
		}//end outer for loop
		//												CHECK AXIS (/)
		for (int counter1 =  winscore-1, counter2 = 0;counter1>=0 && counter2< winscore;counter1--, counter2++){
			int score45degrees = 0; //score for axis (/)
			for (int row=rowNum-counter1, col=colNum-counter1;row<=rowNum  + counter2 && col<=colNum + counter2;row++, col++) {
				for ( Row tempRow : grid.theGrid) {
					for (Box tempSquare : tempRow.theRow) {
						if (tempRow.getNumber() == row && tempSquare.getNum() == col && (tempSquare.toString() == null ? item == null : tempSquare.toString().equals(item))) {
							score45degrees++;
						}//end if
						if (score45degrees ==  winscore) {
							fourInaDiagonal = true;
							return fourInaDiagonal;
						}//end if
					}//end Box loop
				}//end Row loop 
			}//end loop
		}//end loop
		// 												CHECK AXIS (\)
		for (int counter1 =  winscore-1, counter2 = 0;counter1>=0 && counter2< winscore;counter1--, counter2++){
			int score135degrees = 0; //score for axis (\)
			for (int row=rowNum + counter1, col=colNum-counter1;row>=rowNum - counter2 && col<=colNum + counter2;row--, col++) {
				for ( Row tempRow : grid.theGrid) {
					for (Box tempSquare : tempRow.theRow) {
						if (tempRow.getNumber() == row && tempSquare.getNum() == col && tempSquare.toString() == item) {
							score135degrees++;
						}//end if
						if (score135degrees ==  winscore) {
							fourInaDiagonal = true;
							return fourInaDiagonal;
						}//end if
					}//end Box loop
				}//end Row loop 
			}//end loop
		}//end loop
		return fourInaDiagonal;
	}

	//method to check if the grid is full
	public   boolean checkIfGridIsFull (Matrix grid) {
		boolean full = false;
		int gridCounter = 0;
		for (Row tempRow: grid.theGrid) {
			for (Box tempSquare : tempRow.theRow) {
				if (tempSquare.getEmpty() == false) {
					gridCounter++;
					//System.out.println(gridCounter);
				}//end if
			}//end inner loop
		}//end outer loop
		if (gridCounter == filled) {
			JOptionPane.showMessageDialog(null, "THE GRID IS FULL - IT'S A DRAW!");
			full = true;
		}
		return full;
	}//end checkIfGridIsFull

	//method to change players after they play	
	public  Disc changePlayer(Disc player1, Disc player2, Disc tempDisc) {
            
		if (tempDisc == player1) {
			tempDisc = player2;
		} else {
			tempDisc = player1;
		}
		return tempDisc;
	}

	//method to show winner
	public   void showWinner(Disc player1, Disc player2, Disc tempDisc){	
		if (tempDisc == player1){
			JOptionPane.showMessageDialog(null, p2+", YOU WIN.");
		}//end if
		else {
			JOptionPane.showMessageDialog(null, p1+", YOU WIN.");
		}//end else
	}//end showWinner
	
	
}