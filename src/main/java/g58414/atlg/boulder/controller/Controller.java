package g58414.atlg.boulder.controller;

import g58414.atlg.boulder.model.Board;
import g58414.atlg.boulder.model.BoulderDash;
import g58414.atlg.boulder.model.elements.Directions;
import g58414.atlg.boulder.consoleview.ViewConsole;

import java.util.Scanner;
import java.util.regex.Pattern;



/**
 * controller of the boulder dash.
 */
public class Controller {

    private int level = 1;
    private BoulderDash boulderDash = new BoulderDash(level);

    private ViewConsole view = new ViewConsole(boulderDash);
    private Board board;


    /**
     * constructor of the class controller
     */
    public Controller() {
        start();
    }

    /**
     * starts the game
     */
    public void start() {
        boolean isAlive = true;
        view.displayTitle();
        Scanner clavier = new Scanner(System.in);
        board = boulderDash.getBoard();
        boulderDash.start();

        while (isAlive) {
            try {
                view.displayDiamond(board.getLvlLoad(), board.getPlayer());

                view.displayLives(board.getPlayer());
                view.displayLevel(boulderDash);
                view.displayInstructions();
                String command = clavier.nextLine().toLowerCase();


                Pattern p = Pattern.compile("z|s|q|d|undo|redo|resign");
                if (!p.matcher(command).matches()) {
                    System.out.println("Invalid entry");
                    command = clavier.nextLine().toLowerCase();
                }

                char move = command.charAt(0);
                switch (move) {
                    case 'z':
                        boulderDash.playMove(Directions.UP);
                        break;
                    case 's':
                        boulderDash.playMove(Directions.DOWN);
                        break;
                    case 'q':
                        boulderDash.playMove(Directions.LEFT);
                        break;
                    case 'd':
                        boulderDash.playMove(Directions.RIGHT);
                        break;
                }

                if (command.equals("undo")) {
                    view.displayUndo();
                    boulderDash.undo();
                }
                if (command.equals("redo")) {
                    view.displayRedo();
                    boulderDash.redo();
                }
                if (command.equals("resign")) {
                    isAlive = false;
                    System.exit(0);
                }
                if (boulderDash.isLvlWin(board)) {
                    view.displaysExit();
                }
                if (boulderDash.isGameOver() == true) {
                    isAlive = false;
                    view.displayLost();
                    System.exit(0);
                }
                if (boulderDash.isPlayerOnExit()) {

                    boulderDash.nextLevel(board);
                    boulderDash.start();
                    view.displayNextLevel();
                }
                if (boulderDash.isGameWin()) {
                    view.displayWin();
                    System.exit(0);
                }
                if (boulderDash.isGameOver()) {
                    isAlive = false;
                    view.displayLost();
                    System.exit(0);
                }

            } catch (Exception e) {
                view.displayError(e.getMessage());
            }

        }

    }
}


