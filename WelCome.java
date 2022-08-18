package connectfour;

import javax.swing.JOptionPane;

public class WelCome {

    public static void main(String[] args) {
        //Create a GamePlay [currentGame] object and welcome the user
        GamePlay currentGame;
        int reply = welcome();

        //Act according to the user's selection [New Game(0)]
        if (reply == 0) {
            currentGame = new GamePlay();
            currentGame.startGame();
            //call the saveGame method after the current ends or stops by the user
        } else {
            JOptionPane.showMessageDialog(null, "Thank You!");

        }

    }

    //First stage - Welcome message (WelCome new game )
    public static int welcome() {
        String[] options = {"New Game", "Exit"};
        int reply = JOptionPane.showOptionDialog(null, "To win Connect Four you must be the first player to get four of your coloured \n checkers in "
                + "a row either horizontally or vertically.", "Welcome to the Connect Four game", JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return reply;
        //end welcome method

        //Method to save the current status of the game
    }

}
