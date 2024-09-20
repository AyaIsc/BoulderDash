package g58414.atlg.boulder.fxview.component;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * creates the menu at the beginning of the game
 */
public class Menu extends VBox {
    private MenuItem lvl1;
    private MenuItem lvl2;
    private MenuItem lvl3;
    private MenuItem lvl4;

    /**
     * constructor of the class menu that initialize the menu's choice
     */
    public Menu() {
        Image img = new Image("Boulder.gif", 350, 500, true, true);
        ImageView view = new ImageView(img);
        view.setTranslateY(-90);
        this.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        this.getChildren().add(view);
        initChoicelvl();
    }

    /**
     * initializes the level's choices
     */
    private void initChoicelvl() {


        Label levelSelection = new Label("choose your level : ");
        Font levelFont = Font.loadFont("file:src/main/resources/Boulder Dash 6128.ttf", 20);
        levelSelection.setStyle("-fx-effect: dropshadow( one-pass-box , #685fb8, 5 , 34, 0, 0);");
        levelSelection.setTextFill(Color.WHITE);
        levelSelection.setFont(levelFont);
        this.getChildren().add(levelSelection);
        levelSelection.setTranslateY(-30);


        Image imgLevel = new Image("Level.png");
        ImageView level = new ImageView(imgLevel);

        MenuButton lvlChoice = new MenuButton("", level);
        this.setAlignment(Pos.CENTER);

        Image img1 = new Image("LEVELONE.png");
        ImageView levelOne = new ImageView(img1);
        lvl1 = new MenuItem("", levelOne);


        Image img2 = new Image("LEVELTWO.png");
        ImageView levelTwo = new ImageView(img2);
        lvl2 = new MenuItem("", levelTwo);

        Image img3 = new Image("LEVELTHREE.png");
        ImageView levelThree = new ImageView(img3);
        lvl3 = new MenuItem("", levelThree);

        Image img4 = new Image("LEVELFOUR.png");
        ImageView levelFour = new ImageView(img4);
        lvl4 = new MenuItem("", levelFour);

        lvlChoice.getItems().addAll(lvl1, lvl2, lvl3, lvl4);
        this.getChildren().add(lvlChoice);

    }

    /**
     * getter for the menu items
     *
     * @return first menu items
     */
     MenuItem getLvl(int i) {

        switch (i){
            case 1:
                return lvl1;
            case 2:
                return lvl2;
            case 3 :
                return lvl3;
            default:
                return lvl4;
        }
    }

}
