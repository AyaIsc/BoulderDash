package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.BoulderDash;
import g58414.atlg.boulder.model.util.Observer;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CopyScore implements Observer {
    private PlayerScore score;
    private BoulderDash dash;
    private Stage stage;

    public CopyScore(BoulderDash dash ){
        this.dash=dash;
        dash.register(this);
        displayCopy();
    }
    void displayCopy(){

        VBox box = new VBox();

        score= new PlayerScore(dash);

        box.getChildren().add(score);

        Scene copyScene = new Scene(box);
        //copyScene.setRoot(score);
        stage = new Stage();
        stage.setScene(copyScene);
        stage.show();

    }

    @Override
    public void update() {
        score.update();
    }
}
