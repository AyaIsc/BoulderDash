package g58414.atlg.boulder.fxview;

import g58414.atlg.boulder.fxview.component.*;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

/**
 * fx view of the boulder dash
 */
public class BoulderFxView extends GridPane {

    /**
     * constructor of the class BoulderFxView
     * @param primaryStage
     */
    public BoulderFxView(Stage primaryStage) {
        start(primaryStage);

    }

    /**
     * start of the class BoulderFxView
     * @param primaryStage
     */
    private void start(Stage primaryStage){

        SelectionView selectionView = new SelectionView(primaryStage);
        //Scene scene = new Scene(selectionView,500,500);

        //selectionView.setAlignment(Pos.CENTER);
        //primaryStage.setScene(scene);
        primaryStage.show();

    }

}
