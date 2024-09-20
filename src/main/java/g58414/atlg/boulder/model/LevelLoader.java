package g58414.atlg.boulder.model;

import g58414.atlg.boulder.model.elements.*;

import java.io.*;
import java.util.Scanner;

/**
 * loads the chosen level
 */
public class LevelLoader {
    private final Player player;
    private int height;
    private int width;
    private Element[][] board;
    private Position exitPos;
    private Position startPos;

    private File file;
    private String PATH_LEVEL_1 = "src/main/resources/levels/Lvl1.txt";
    private String PATH_LEVEL_2 = "src/main/resources/levels/Lvl2.txt";
    private String PATH_LEVEL_3 = "src/main/resources/levels/Lvl3.txt";
    private String PATH_LEVEL_4 = "src/main/resources/levels/Lvl4.txt";
    private String PATH_LEVEL_TEST = "src/main/resources/levels/LvlTest.txt";
    private int diam;
    private String nom;
    private static final String SOIL = "S";
    private static final String ROCK = "R";
    private static final String DIAMOND = "D";
    private static final String WALL = "W";
    private static final String PLAYER = "I";
    private static final String EXIT = "E";
    private static final String NOTHING = " ";
    private int levelNum; //needed

    /**
     * constructor of the class level loader
     *
     * @param levelNum number of the chosen level
     * @param player   player
     */
    public LevelLoader(int levelNum, Player player) {
        this.player = player;
        switch (levelNum) {
            case 1:
                file = new File(PATH_LEVEL_1);
                generateLevel();
                break;
            case 2:
                file = new File(PATH_LEVEL_2);
                generateLevel();
                break;
            case 3:
                file = new File(PATH_LEVEL_3);
                generateLevel();
                break;
            case 4:
                file = new File(PATH_LEVEL_4);
                generateLevel();
                break;
            case 0:
                file = new File(PATH_LEVEL_TEST);
                generateLevel();
                break;
        }
    }

    /**
     * generates the level by going through it and giving its information
     *
     */
    public void generateLevel() {
        try {
            Scanner temp = new Scanner(file);

            nom = temp.nextLine();

            diam = Integer.parseInt(temp.nextLine());


            String s = temp.nextLine();

            height = 0;
            width = s.trim().split(",").length;

            while (temp.hasNextLine()) {
                temp.nextLine();
                height++;
            }

            height++;

            board = new Element[width][height];


            BufferedReader in = new BufferedReader(new FileReader(file));
            in.readLine();
            in.readLine();

            for (int j = 0; j < height; j++) { // faire ça dans une classe LevelLoader

                String[] line = in.readLine().split(",");
                for (int i = 0; i < line.length; i++) {

                    String element = line[i];

                    switch (element) {
                        case SOIL: // constante: private static final String SOIL = "S";
                            board[i][j] = new Soil();
                            break;
                        case ROCK:
                            board[i][j] = new Rock();
                            break;
                        case DIAMOND:
                            board[i][j] = new Diamond();
                            break;
                        case WALL:
                            board[i][j] = new Wall();
                            break;
                        case PLAYER:
                            player.setPosition(new Position(i, j));
                            board[i][j] = player;
                            startPos = new Position(i, j);
                            break;
                        case EXIT:
                            board[i][j] = new Nothing();
                            exitPos = new Position(i, j);
                            break;
                        case NOTHING:
                            board[i][j] = new Nothing();
                            break;

                    }
                }
            }
        } catch (IOException e) {
            System.out.println("file not found");
        }
    }

    /**
     * getter for the level's height
     *
     * @return level's height
     */
    public int getHeight() {
        return height;
    }

    /**
     * getter for the level's width
     *
     * @return level's width
     */
    public int getWidth() {
        return width;
    }

    /**
     * getter for the level's file
     *
     * @return level's file
     */
    public File getFile() {
        return file;
    }

    /**
     * getter for the level's diamonds
     *
     * @return level's (number of ) diamonds
     */
    public int getDiam() {
        return diam;
    }

    /**
     * getter for the level's name
     *
     * @return level's name
     */
    public String getNom() {
        return nom;
    }


    /**
     * getter for the level's number
     *
     * @return level's number
     */
    public int getLevelNum() {
        return levelNum;
    }

    /*public void setLevelNum(int levelNum) {
        this.levelNum = levelNum;
    }*/ //pas l'air de servir

    /**
     * Copy of the board that is being used for undos and redos
     *
     * @return copy of the board ( not the actual one )
     */
     Element[][] oldBoard() {


        Element[][] oldBoard = new Element[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (board[i][j] instanceof Rock) {
                    oldBoard[i][j] = new Rock();
                } else if (board[i][j] instanceof Diamond) {
                    oldBoard[i][j] = new Diamond();
                } else if (board[i][j] instanceof Wall) {
                    oldBoard[i][j] = new Wall();
                } else if (board[i][j] instanceof Soil) {
                    oldBoard[i][j] = new Soil();
                } else if (board[i][j] instanceof Player) {
                    oldBoard[i][j] = new Player();
                } else if (board[i][j] instanceof Exit) {
                    oldBoard[i][j] = new Exit();
                } else if (board[i][j] instanceof Nothing) {
                    oldBoard[i][j] = new Nothing();
                }
            }
        }
        return oldBoard;
    }

    /**
     * getter of the element
     *
     * @param position of the wanted element
     * @return the element
     */
    public Element getElemAt(Position position) {

        int x = position.getX();
        int y = position.getY();
        return board[x][y];
    }

    /**
     * setter of the element
     *
     * @param pos position of the element
     * @param e   wanted element
     */
    void setElement(Position pos, Element e) { // pas public
        int y = pos.getY();
        int x = pos.getX();
        board[x][y] = (e);
    }

    /**
     * removes the element from the board by replacing it with the element nothing
     *
     * @param pos position of the element that needs to be removed
     */
    void removeElement(Position pos) { // pas public
        int y = pos.getY();
        int x = pos.getX();
        board[x][y] = new Nothing();
    }

    /**
     * setter of the board
     *
     * @param oldBoard that needs to be set for undo/redo
     */
    void setBoard(Element[][] oldBoard) {
        this.board = oldBoard;
    }

    /**
     * getter for the exit's position
     *
     * @return exit's position
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


}



