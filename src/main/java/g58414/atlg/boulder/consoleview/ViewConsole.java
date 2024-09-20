package g58414.atlg.boulder.consoleview;


import g58414.atlg.boulder.model.*;
import g58414.atlg.boulder.model.elements.*;

/**
 * class that contains all the needed displays for the game
 */
public class ViewConsole {
    private BoulderDash dash;

    /**
     * constructor of the class view Console
     *
     * @param dash the facade boulder dash
     */
    public ViewConsole(BoulderDash dash) {
        this.dash = dash;
    }

    /**
     * displays the game's title
     */
    public void displayTitle() {
        System.out.println("*************************************\n" +
                "           boulder dash!\n" +
                "*************************************");
    }

    /**
     * notices the user of passing the level
     */
    public void displayNextLevel() {
        System.out.println("you passed to the next level");
    }

    /**
     * indicates the user to get to the exit after getting enough diamonds
     */
    public void displaysExit() {
        System.out.println("You got all your diamonds , the exit is open , go to it for the next level");
    }

    /**
     * reminds user of an undo
     */
    public void displayUndo() {
        System.out.println("undo done");
    }

    /**
     * reminds user of a redo
     */
    public void displayRedo() {
        System.out.println("redo done");
    }

    /**
     * displays the board
     *
     * @param dash board that needs to be displayed
     */
    public void displayLevel(BoulderDash dash) {
        Board board = dash.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();

        String elem = " ";
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (board.getElemAt(new Position(i, j)) instanceof Rock) {
                    elem = "R";
                } else if (board.getElemAt(new Position(i, j)) instanceof Diamond) {
                    elem = "D";
                } else if (board.getElemAt(new Position(i, j)) instanceof Wall) {
                    elem = "W";
                } else if (board.getElemAt(new Position(i, j)) instanceof Soil) {
                    elem = "S";
                } else if (board.getElemAt(new Position(i, j)) instanceof Player) {
                    elem = "I";
                } else if (board.getElemAt(new Position(i, j)) instanceof Exit) {
                    elem = "E";
                } else if (board.getElemAt(new Position(i, j)) instanceof Nothing) {
                    elem = " ";
                }
                System.out.print(elem);
            }
            System.out.println();
        }

    }

    /**
     * indicates user when they lost
     */
    public void displayLost() {
        System.out.println("you lost , thank you for playing.");
    }


    /**
     * displays the number of diamonds needed to get to the exit ,
     * displays a message when they get enough diamonds
     *
     * @param level  for the level's diamonds
     * @param player for the player's diamonds
     */
    public void displayDiamond(LevelLoader level, Player player) {
        System.out.println(player.getDiamonds() + "/" + level.getDiam() + " diamond to get for exit");
        if (level.getDiam() == player.getDiamonds()) {
            System.out.println("you got the diamondsss!!!!");
        }
    }

    /**
     * inform the user to how to play
     */
    public void displayInstructions() {
        System.out.println("enter a movement (z=up,s=down,d=right,q=left)" + "\n" + " or an undo/redo " + "\n" +
                "or 'resign' if you want to abandon : ");
    }

    /**
     * displays the users lives
     *
     * @param player
     */
    public void displayLives(Player player) {
        System.out.println(player.getLives() + "/3 lives");
    }

    /**
     * displays when the user wins the game
     */
    public void displayWin() {
        System.out.println("you reached the end , thank you for playing :)");
    }

    public void displayError(String message) {
        System.out.println("an error was detected");
    }
}
