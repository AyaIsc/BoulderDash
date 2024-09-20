package g58414.atlg.boulder.fxview.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Page when the players loose all its lives (meaning that the game is over for him)
 */

public class GameOver extends VBox {

    private Label goBack;

    /**
     * constructor of the page game Over
     */
    public GameOver() {
        initNoLives();
    }

    /**
     * initializes the page with the text and image
     */
    private void initNoLives() {
        Image img = new Image("gmoverr.gif", 700, 500, false, false);
        ImageView gameOver = new ImageView(img);

        Label end = new Label("You lost all of your lives </3");
        end.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "#8a5143\n , 5 , 34, 0, 0 );");
        Font font = Font.loadFont("file:src/main/resources/Boulder Dash 6128.ttf", 20);
        end.setFont(font);

        goBack = new Label("Go back to the Home page by clicking here ! ");
        goBack.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "white\n , 5 , 34, 0, 0 );");
        goBack.setFont(font);

        this.getChildren().addAll(gameOver, end, goBack);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);


    }

    /**
     * getter of the label that needs to be clicked on
     * if the players want to go back to the home page
     *
     * @return label to go back to the home page
     */
    Label getGoBack() {
        return goBack;
    }
}
