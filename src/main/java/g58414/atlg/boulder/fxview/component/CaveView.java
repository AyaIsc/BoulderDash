package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.*;
import g58414.atlg.boulder.model.elements.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * cave view of the level's board
 */
public class CaveView extends GridPane {

    private BoulderDash dash;
    private static int sizeTile = 32;
    private static final Image IMAGE_WALL = new Image("Border.png",sizeTile, sizeTile, true, true);
    private static final Image IMAGE_SOIL = new Image("Earth.png",sizeTile, sizeTile, true, true);
    private static final Image IMAGE_ROCK = new Image("Rock3.png",sizeTile, sizeTile, true, true);
    private static final Image IMAGE_DIAMOND = new Image("Diamond3.gif",sizeTile,sizeTile,true,true);
    private static final Image IMAGE_EMPTY = new Image("Empty.png",sizeTile,sizeTile,true,true);
    private static final Image IMAGE_EXIT = new Image("Exit.png",sizeTile,sizeTile,true,true);
    private static final Image IMAGE_PLAYER = new Image("perso.gif",sizeTile,sizeTile,true,true);

    /**
     * constructor of the CaveView
     *
     * @param dash
     */
    public CaveView(BoulderDash dash) {
        this.dash = dash;
    }

    /**
     * displays the board and assigns for each element its image
     */
     void displayLevel() {

        Board board = dash.getBoard();
        int width = board.getWidth();
        int height = board.getHeight();


        String imgz = " ";
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                if (board.getElemAt(new Position(i, j)) instanceof Rock) {
                    this.add(new ImageView(IMAGE_ROCK),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Diamond) {
                    this.add(new ImageView(IMAGE_DIAMOND),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Wall) {
                    this.add(new ImageView(IMAGE_WALL),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Nothing) {
                    this.add(new ImageView(IMAGE_EMPTY),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Soil) {
                    this.add(new ImageView(IMAGE_SOIL),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Exit) {
                    this.add(new ImageView(IMAGE_EXIT),i, j);
                }
                if (board.getElemAt(new Position(i, j)) instanceof Player) {
                    this.add(new ImageView(IMAGE_PLAYER),i, j);
                }
                //Image img = new Image(imgz, sizeTile, sizeTile, true, true);
                //this.add(new ImageView(img), i, j);
            }

        }


    }

    /**
     * checks if the player is on the exit and goes to the next level if it is
     *
     */
     private void playerOnExit() {
        if (dash.isPlayerOnExit()) {
            int level = dash.getBoard().getLevel();
            Board newBoard = new Board(level + 1);
            dash.nextLevel(newBoard);
        }

    }

    /**
     * creates exit if the player gets enough diamonds
     */
    private void exit() {

        if (dash.isLvlWin(dash.getBoard())) {
        }
    }

    /**
     * clears the board for better movements quality
     */
     void clear() {
        this.getChildren().clear();
    }


    /**
     * updates all the changes
     *
     */
     void update() {
        exit();
        playerOnExit();
        displayLevel();
    }


}
