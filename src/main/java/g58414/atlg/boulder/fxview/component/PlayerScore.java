package g58414.atlg.boulder.fxview.component;

import g58414.atlg.boulder.model.BoulderDash;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * component of the player's score
 */
public class PlayerScore extends VBox {


    private Label lives;
    private Label level;
    private Label diamond;

    private Font myfont = Font.loadFont("file:src/main/resources/Boulder Dash 6128.ttf", 20);

    private Image imgDiamond = new Image("Diamond2.png",70,25,false,false);
    private ImageView viewDiamond = new ImageView(imgDiamond);
    private Image imgLives = new Image("heart.png",30,25,false,false);
    private ImageView viewLives = new ImageView(imgLives);
    private BoulderDash dash;

    /**
     * constructor of the player's score
     * @param dash to initialize the player's score
     */
    public PlayerScore(BoulderDash dash) {
        this.dash = dash;

        initLvl();
        initDiam();
        initLives();

        HBox playerScores = new HBox();
        playerScores.getChildren().addAll(diamond,viewDiamond);
        playerScores.getChildren().addAll(lives,viewLives);

        playerScores.setAlignment(Pos.CENTER);
        this.getChildren().addAll(playerScores);

    }

    /**
     * initializes the level's name
     */
    private void initLvl() {
        String text = dash.getBoard().getLevelName();
        level = new Label(text);
        Font levelFont = Font.loadFont("file:src/main/resources/Boulder Dash 6128.ttf", 30);
        level.setStyle("-fx-effect: dropshadow( one-pass-box , #685fb8, 5 , 34, 0, 0);"); //ikd , flou, -gauche/+droite,-haut,+bas
        level.setTextFill(Color.WHITE);
        level.setFont(levelFont);


        this.getChildren().add(level);

    }

    /**
     * initializes the level's diamonds
     */
    private void initDiam() {
        int playerDiamonds = dash.getBoard().getPlayer().getDiamonds();
        int levelDiamonds = dash.getBoard().getNbDiamond();
        diamond = new Label( playerDiamonds+
                "/" + levelDiamonds );

        diamond.setTextFill(Color.web("#857b79"));
        diamond.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "#8a5143\n , 5 , 34, 0, 0 );");
        diamond.setFont(myfont);

    }

    /**
     * initializes the level's lives
     */
    private void initLives() {
        lives = new Label(dash.getBoard().getPlayer().getLives() + "/3");

        lives.setTextFill(Color.web("#857b79"));
        lives.setStyle("-fx-effect: dropshadow( one-pass-box , \n" +
                "#8a5143\n , 5 , 34, 0, 0 );");
        lives.setFont(myfont);
    }

    /**
     * updates on every change
     */
    void update() {
        diamond.setText(dash.getBoard().getPlayer().getDiamonds()
                + "/" + dash.getBoard().getNbDiamond());
        diamond.setFont(myfont);
        lives.setText(dash.getBoard().getPlayer().getLives() + "/3");
        lives.setFont(myfont);
        level.setText(dash.getBoard().getLevelName());

    }

}
