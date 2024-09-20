package g58414.atlg.boulder.model;

import g58414.atlg.boulder.model.elements.*;
import g58414.atlg.boulder.model.util.CommandManager;

import java.io.FileNotFoundException;

/**
 * Board that contains the elements of the level and the player and its elements
 */
public class Board {

    private Element[][] board;
    private Player player;
    private String levelName;
    private int nbDiamond;

    private int level;
    private int height;
    private int width;
    private Position exitPos;
    private Position startPos;
    private LevelLoader lvlLoad;
    private int levelNum;
    private boolean isRestart ;

    /**
     * constructor of the class board
     *
     * @param level number of the chosen level
     */
    public Board(int level) {
        this.level = level;
        this.player = new Player();

        lvlLoad = new LevelLoader(level, player);

        levelName = lvlLoad.getNom();
        nbDiamond = lvlLoad.getDiam();

        width = lvlLoad.getWidth();
        height = lvlLoad.getHeight();
        exitPos = lvlLoad.getExitPos();
        startPos = lvlLoad.getStartPos();
        levelNum = lvlLoad.getLevelNum();


    }

    /**
     * restarts level when the player dies
     */
    public void restartLevel() {

        this.lvlLoad= new LevelLoader(level,player);
        player.setDiamonds(0);
        isRestart = true;

    }

    /**
     * resets the boolean isRestart so the clear stack doesn't clear continuously
     */
    public void resetIsRestart(){
        isRestart=false;
    }

    /**
     * getter of an element in the board
     *
     * @param position of the element
     * @return the asked element
     */
    public Element getElemAt(Position position) {
        return lvlLoad.getElemAt(position);
    }

    /**
     * setter for usages in the move Command
     *
     * @param pos Position of the element
     * @param e   element that needs to be set
     */
    void setElement(Position pos, Element e) { // pas public
        lvlLoad.setElement(pos, e);
    }

    /**
     * Remover of elements for usages in the class MoveCommand
     *
     * @param pos position of the element to remove
     */
    void removeElement(Position pos) { // pas public
        lvlLoad.removeElement(pos);
    }

    /**
     * creates exit when the players get enough diamonds
     */
    void createExit() {
        lvlLoad.setElement(lvlLoad.getExitPos(), new Exit());
    }


    /**
     * getter for the player
     *
     * @return the player
     */
    public Player getPlayer() {
        return player;
    }



    /**
     * getter for the level loader
     *
     * @return
     */
    public LevelLoader getLvlLoad() {
        return lvlLoad;
    }

    /**
     * for undo and redo
     * @param lvlLoad
     */
    public void setLvlLoad(LevelLoader lvlLoad) {
        this.lvlLoad = lvlLoad;
    }

    /**
     * Getter of the board's height.
     *
     * @return the board's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Getter of the board's width.
     *
     * @return board's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter of the level
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * setter for the level
     *
     * @param level that needs to be set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * getter for the level's number of diamonds
     *
     * @return number of diamonds
     */
    public int getNbDiamond() {
        return nbDiamond;
    }

    /**
     * gttter for the exit's position
     *
     * @return the exit's position
     */
    public Position getExitPos() {
        return exitPos;
    }

    /**
     * getter for the start's position
     *
     * @return start's position
     */
    public Position getStartPos() {
        return startPos;
    }

    /**
     * getter for the level's name
     *
     * @return
     */
    public String getLevelName() {
        return levelName;
    }

    /**
     * checks if the level restarted to clear the stacks.
     *
     * @return boolean true if it restarted false if it didn't
     */
    boolean isRestart() {
        return isRestart;
    }


}
