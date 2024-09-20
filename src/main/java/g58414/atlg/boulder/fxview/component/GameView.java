package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.BoulderDash;
import g58414.atlg.boulder.model.util.Observer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

/**
 * view of te game with all its component
 */
public class GameView extends VBox implements Observer {

    private CaveView caveView;
    private PlayerScore playerScore;
    private UndoRedo undoRedo;

    private final Menu menu = new Menu();
    private Image resign; 
    private ImageView resignView;


    /**
     * constructor of the class GameView that initializes the components
     *
     * @param dash to initialize each component
     */
    public GameView(BoulderDash dash) {
        initExit(this);
        this.caveView = new CaveView(dash);
        this.playerScore = new PlayerScore(dash);
        this.undoRedo = new UndoRedo(dash);
        playerScore.setAlignment(Pos.CENTER);
        caveView.setAlignment(Pos.CENTER);
        undoRedo.setAlignment(Pos.CENTER);
        initResign();


        dash.register(this);


        this.getChildren().addAll(playerScore, caveView, undoRedo, resignView);
        caveView.displayLevel();


        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

    }

    /**
     * updates on every change in our components
     */
    @Override
    public void update() {
        caveView.clear();
        playerScore.update(); //scoreP.update(dash)
        caveView.update();

    }

    /**
     * initializer for the resignation button to quit the level for the home page
     */
    private void initResign() {

        resign = new Image("resign.png", 140, 45, false, false);
        resignView = new ImageView(resign);

    }

    /**
     * initializer for the exit if the player wants to quit the game
     *
     * @param v
     */
    private void initExit(VBox v) {

        Image img = new Image("bye.png", 35, 35, false, false);
        ImageView exit = new ImageView(img);

        exit.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Platform.exit();
            }
        });


        v.getChildren().add(exit);
    }

    /**
     * getter for the resign
     *
     * @return resign image
     */
     ImageView getResignView() {
        return resignView;
    }


    /*
    - represente le jeu: Cave, le score et les boutons udo redo

     */
}
