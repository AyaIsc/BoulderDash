package g58414.atlg.boulder.model;

import g58414.atlg.boulder.model.util.Command;
import g58414.atlg.boulder.model.elements.*;

import java.io.FileNotFoundException;

/**
 * class where are the movement are made.
 */
public class MoveCommand implements Command { //alt enter
    private Player player;
    private Directions directions;

    private Element[][] oldBoard;

    private Board board;

    private int oldDiamonds;
    private Position oldPosition;
    private BoulderDash dash;

    private Position nextMove;


    /**
     * constructor of the class moveCommand.
     *
     * @param
     * @param dir   direction of the movement done by the player
     */
    public MoveCommand(BoulderDash dash, Directions dir) {
        this.board = dash.getBoard();
        this.player = board.getPlayer();
        this.oldBoard = board.getLvlLoad().oldBoard();
        this.directions = dir;
        this.oldPosition = board.getPlayer().getPosition();
        this.oldDiamonds = board.getPlayer().getDiamonds();
        //this.oldPos= board.getLvlLoad().getPlayer().getPosition();
    }

    /**
     * executes the movement in different cases.
     *
     */
    @Override
    public void execute() {
        nextMove = board.getPlayer().getPosition().next(directions);

        //case normal and soil
        if ((board.getElemAt(nextMove) instanceof Nothing) || (board.getElemAt(nextMove) instanceof Soil)) {
            normalMove();
        }
        // case Rock
        if (board.getElemAt(nextMove) instanceof Rock) { //pas new object
            if (board.getElemAt(nextMove.next(directions)) instanceof Nothing) {
                rockCase();
            }
        }
        //diamond case
        if (board.getElemAt(nextMove) instanceof Diamond) {
            diamondCase();
        }
        //exit case
        if (board.getElemAt(nextMove) instanceof Exit) {
            exitCase();
        }

        //element on element command
        for (int i = 0; i < board.getWidth(); i++) {
            for (int j = 0; j < board.getHeight(); j++) {
                elementOnElement(new Position(i, j), Directions.LEFT);
                elementOnElement(new Position(i, j), Directions.RIGHT);

            }
        }


    }

    /**
     * manages the movements of diamonds and rocks when they need to fall
     *
     * @param positionElement position of the element
     * @param dir             LEFT or RIGHT to check both sides
     */
    private void elementOnElement(Position positionElement, Directions dir) {
        emptyUnderElement(positionElement);
        elementUnderElement(positionElement, dir);
    }

    /**
     * check if there's nothing under the element , which is a diamond or a rock.
     * then it manages its fall
     *
     * @param positionElement the element's position
     */
    private void emptyUnderElement(Position positionElement) {
        if ((board.getElemAt(positionElement) instanceof Rock || board.getElemAt(positionElement) instanceof Diamond)
                && (board.getElemAt(positionElement.next(Directions.DOWN)) instanceof Nothing)) {
            board.setElement(positionElement.next(Directions.DOWN), board.getElemAt(positionElement));
            board.removeElement(positionElement);
            if (board.getElemAt(positionElement.next(Directions.DOWN).next(Directions.DOWN)) instanceof Player) {
                removingElements(positionElement);

                board.restartLevel();
            }
        }
    }

    /**
     * checks if there's an element (rock/diamond) under an element (rock/diamond)
     * on both directions (Left/Right) to see if it's empty next to it so it can fall ,
     * then it manages its fall.
     *
     * @param positionElement element position
     * @param directions             direction that needs to be checked
     */
    private void elementUnderElement(Position positionElement, Directions directions) {
        if ((board.getElemAt(positionElement) instanceof Rock || board.getElemAt(positionElement) instanceof Diamond)
                && (board.getElemAt(positionElement.next(Directions.DOWN)) instanceof Rock || board.getElemAt(positionElement.next(Directions.DOWN)) instanceof Diamond)) {
            if (board.getElemAt(positionElement.next(directions)) instanceof Nothing &&
                    board.getElemAt(positionElement.next(directions).next(Directions.DOWN)) instanceof Nothing) {  //si a cote des pierres rien
                board.setElement(positionElement.next(directions).next(Directions.DOWN), board.getElemAt(positionElement));//place l'elem en bas
                board.removeElement(positionElement);
                if (board.getElemAt(positionElement.next(Directions.DOWN).next(Directions.DOWN)) instanceof Player) {
                    removingElements(positionElement);
                }
            }
        }
    }

    /**
     * removes the element when there's a fall and resets the player positions and diamond
     * when it gets killed
     *
     * @param positionElement
     */
    private void removingElements(Position positionElement) {
        board.removeElement(positionElement.next(Directions.DOWN).next(Directions.DOWN));
        board.removeElement(board.getPlayer().getPosition());
        player.setPosition(board.getStartPos());
        player.setLives(player.getLives() - 1);
        player.setDiamonds(oldDiamonds);
    }

    /**
     * normal movement of the player when he needs to moves to an empty tile or a soil tile
     */
    private void normalMove() {

        board.setElement(player.getPosition(), new Nothing());
        board.setElement(nextMove, player);

        player.setPosition(nextMove);

    }

    /**
     * movement of when the player moves a rock (nothing behind the rock)
     */

    private void rockCase() {

        board.removeElement(nextMove); //enleve rock

        board.setElement(player.getPosition(), new Nothing()); //vide derriere player
        board.setElement(nextMove, player); //bouge player

        board.setElement(nextMove.next(directions), new Rock()); //bouge rock
        player.setPosition(nextMove);

    }

    /**
     * movement of when the player catches a diamond
     */
    private void diamondCase() {
        board.removeElement(nextMove); //enleve diamond

        board.setElement(player.getPosition(), new Nothing()); //vide derriere player
        board.setElement(nextMove, player); //bouge player
        player.setPosition(nextMove);
        int diam = board.getPlayer().getDiamonds();
        board.getPlayer().setDiamonds(diam + 1);

    }

    /**
     * movement of when a player steps on the exit tile
     */
    private void exitCase() {
        board.removeElement(nextMove);
        board.setElement(player.getPosition(), new Nothing()); //vide derriere player
        board.setElement(nextMove, player); //bouge player
        player.setPosition(nextMove);
    }

    /**
     * unexecute for undos that sets the old board and the old player's position
     */
    @Override
    public void unexecute() {

        player.setPosition(oldPosition);
        board.getLvlLoad().setBoard(oldBoard);
        player.setDiamonds(oldDiamonds);

    }


}
