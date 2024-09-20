package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.BoulderDash;
import g58414.atlg.boulder.model.elements.Directions;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * class of the selection of the level
 */
public class SelectionView extends VBox {

    private Stage primaryStage;
    private BoulderDash bd;
    private Menu menu = new Menu();
    private GameView gv;
    private Scene sceneGame;
    private Scene sceneMenu = new Scene(this, 1920, 1080);
    private  CopyScore copyScore;


    /**
     * constructor of the SelectionView
     *
     * @param primaryStage
     */
    public SelectionView(Stage primaryStage) {


        this.bd = new BoulderDash(1);
        this.gv = new GameView(bd);

        this.sceneGame = new Scene(gv, 800, 800);

        this.primaryStage = primaryStage;
        this.primaryStage.setFullScreen(true);

        initMenu();
        movePlayerFx(sceneGame);


        this.setAlignment(Pos.CENTER);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void initMenu() {
        this.getChildren().clear();
        menu = new Menu();
        initQuit();

        this.getChildren().add(menu);
        this.setAlignment(Pos.CENTER);
        this.primaryStage.setFullScreen(true);
        initGame();
        primaryStage.setScene(sceneMenu);

    }

    /**
     * takes the user back to the home page as he resigns/abandon
     */
    private void resign() {
        gv.getResignView().setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent Event) {
                initMenu();
            }
        });

    }

    /**
     * initializes the first level as is it chosen
     */
    private void initGame() {
        for (int i = 1; i <= 4; i++) {
            int finalI = i;
            menu.getLvl(i).setOnAction(new EventHandler<>() {
                @Override
                public void handle(ActionEvent e) {
                    bd = new BoulderDash(finalI);
                    GameView gv1 = new GameView(bd);
                    copyScore= new CopyScore(bd);
                    gv = gv1;

                    gv.setAlignment(Pos.CENTER);

                    sceneGame.setRoot(gv1);

                    resign();
                    primaryStage.setScene(sceneGame);
                    primaryStage.setFullScreen(true);
                }
            });
        }
    }
    private void createCopy(){
        copyScore.displayCopy();
    }

    /**
     * moves the player as the player presses the key
     *
     * @param scene
     */
    private void movePlayerFx(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            switch (e.getCode()) {
                case UP:

                    bd.playMove(Directions.UP);

                    break;
                case DOWN:

                    bd.playMove(Directions.DOWN);

                    break;
                case LEFT:

                    bd.playMove(Directions.LEFT);

                    break;
                case RIGHT:

                    bd.playMove(Directions.RIGHT);

                    break;
                case S:
                    new CopyScore(bd);
                    break;
            }
            initGameOver();
            initGameWin();
        });
    }

    /**
     * initializes the game over page with the label to go back to the menu
     */
    private void initGameOver() {
        if (bd.isGameOver()) {
            GameOver gameOver = new GameOver();

            sceneGame.setRoot(gameOver);
            this.primaryStage.setScene(sceneGame);
            this.primaryStage.setMaximized(true);
            gameOver.getGoBack().setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    initMenu();
                }
            });
        }
    }

    /**
     * initializes page of when the player wins the game with the label to go back to the menu
     */
    private void initGameWin() {
        if (bd.isGameWin()) {
            GameWin gameWin = new GameWin();

            sceneGame.setRoot(gameWin);
            this.primaryStage.setScene(sceneGame);
            this.primaryStage.setMaximized(true);

            gameWin.getGoMenu().setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent mouseEvent) {
                    initMenu();
                }
            });
        }
    }

    /**
     * initializes the quit button to quit the platform from the menu
     */
    private void initQuit() {


        Image img = new Image("quit.png", 35, 35, false, false);
        ImageView quit = new ImageView(img);

        quit.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });

        quit.setTranslateY(-380);
        menu.getChildren().add(quit);

    }

}





