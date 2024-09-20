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
 * page of when the player wins all the levels
 */
public class GameWin extends VBox {
    private Label goBack;

    /**
     * constructor of the class GameWin
     */
    public GameWin() {
        initYouWon();
    }

    /**
     * initializes the page with the image and the components of this page
     */
    private void initYouWon() {
        Image img = new Image("andthewinneris.gif",450,300,false,false);
        ImageView gameOver= new ImageView(img);

        Label end = new Label(
                " YOU!  you reached the end thank you for playing ! :)");
        end.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "white\n , 5 , 34, 0, 0 );");

        Font myFont = Font.loadFont("file:src/main/resources/Boulder Dash 6128.ttf", 20);
        end.setFont(myFont);

        goBack = new Label("Go back to the Home page by cliking here ");
        goBack.setFont(myFont);
        goBack.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "#685fb8\n , 5 , 34, 0, 0 );");
        this.getChildren().addAll(gameOver , end , goBack);

        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);


    }

    /**
     * label that allows the player to go back to the home page and play again.
     * @return
     */
     Label getGoMenu() {
        return goBack;
    }
}
