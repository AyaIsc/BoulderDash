package g58414.atlg.boulder;

import g58414.atlg.boulder.fxview.BoulderFxView;
import javafx.application.Application;
import javafx.stage.Stage;



public class MainFx extends Application {

    /**
     * main of the Javafx Version
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * starts the game
     * @param primaryStage
     */
    public void start(Stage primaryStage){


        BoulderFxView viewFx = new BoulderFxView(primaryStage);


    }
}
