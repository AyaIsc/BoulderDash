package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.BoulderDash;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * component of undo and redo
 */
public class UndoRedo extends HBox {
    private BoulderDash dash;
    private Image img1 = new Image("redo.png", 140, 45, false, false);
    private ImageView viewRedo = new ImageView(img1);
    private Image img2 = new Image("undo.png", 140, 45, false, false);
    private ImageView viewUndo = new ImageView(img2);

    /***
     * constructor of the undo redo class that initializes all the needed components
     * @param dash to initialize the class
     */
    public UndoRedo(BoulderDash dash) {
        this.dash = dash;
        initUndo();
        initRedo();

        this.getChildren().addAll(viewRedo);
        this.getChildren().addAll(viewUndo);
        this.setAlignment(Pos.CENTER);


    }

    /**
     * initializes the redo
     */
    public void initRedo() {

        viewRedo.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent actionEvent) {
                dash.redo();

            }
        });
    }

    /**
     * initializes the undo
     */
    public void initUndo() {


        viewUndo.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent Event) {
                dash.undo();

            }
        });
    }
}
