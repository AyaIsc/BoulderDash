package g58414.atlg.boulder.model;

import g58414.atlg.boulder.model.elements.Directions;

import g58414.atlg.boulder.model.elements.Player;
import g58414.atlg.boulder.model.elements.Position;
import g58414.atlg.boulder.model.util.Command;
import g58414.atlg.boulder.model.util.CommandManager;
import g58414.atlg.boulder.model.util.Observable;
import g58414.atlg.boulder.model.util.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 * facade where all comes through
 */
public class BoulderDash implements Observable {

    private List<Observer> observers = new ArrayList<>();
    private Board board;
    private CommandManager commandManager = new CommandManager();
    private int level;

    /**
     * constructor of the class boulder dahs
     *
     * @param level chosen level
     */
    public BoulderDash(int level) {
        this.level =level;

        this.board = new Board(this.level);
    }

    /**
     * starts the game
     */
    public void start() {
        board.getPlayer().setDiamonds(0);
        board.getPlayer().setLives(3);

        notifyObserver();
    }


    /**
     * checks if the game is over
     *
     * @return true if the game is over false if its not
     */
    public boolean isGameOver() {
        return board.getPlayer().getLives() == 0;

    }

    /**
     * Checks if the player won the level
     *
     * @param board where the player is playing
     * @return true if he won false if not
     */
    public boolean isLvlWin(Board board) {
        if (board.getNbDiamond() == board.getPlayer().getDiamonds()) {
            board.createExit();//OK cool
            return true;
        }
        return false;
    }

    /**
     * Moves the player in the direction they choose
     *
     * @param dir direction of the movement
     */
    public void playMove(Directions dir) {
        Command moveCommand = new MoveCommand(this, dir);
        commandManager.addCommand(moveCommand);

        movesRestart();
        notifyObserver();

    }

    /**
     * checks if the level is restarted to clear the stacks
     */
    private void movesRestart(){
        if(board.isRestart()){
            commandManager.clearStack();
            board.resetIsRestart();
        }

    }

    /**
     * checks if the player is on the exit
     *
     * @return true if it is false if it's not on the exit
     */
    public boolean isPlayerOnExit() {

        return board.getPlayer().getPosition().equals(board.getExitPos());

    }

    /**
     * informs us if the user won the game
     * @return boolean true if he did , false if he didn't
     */
    public boolean isGameWin(){
        return board.getLevel() == 5;
    }


    /**
     * undo the movement
     */
    public void undo() {
        commandManager.undo();
        notifyObserver();
    }

    /**
     * redo the movement
     */
    public void redo() {
        commandManager.redo();
        notifyObserver();
    }


    /**
     * getter for the board
     *
     * @return the board
     */
    public Board getBoard() {
        return board;
    }


    /**
     * method that brings the user to the next level
     *
     * @param board that needs to be set
     */
    public void nextLevel(Board board) {
        board = new Board(board.getLevel() + 1);
        this.board = board;
        notifyObserver();
    }


    /**
     * registers the observer
     *
     * @param observer
     */
    @Override
    public void register(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * unregisters the observer
     *
     * @param observer
     */
    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    /**
     * notifies of any changes
     *
     */
    private void notifyObserver() {
        for (Observer obs : observers) {
            obs.update();
        }
    }
}
