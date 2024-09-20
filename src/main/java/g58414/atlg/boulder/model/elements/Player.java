package g58414.atlg.boulder.model.elements;

/**
 * The element player of the boulder dash
 */
public class Player extends Element {


    private int diamands;
    private int lives;
    private boolean isDead = false;
    private Position positionPlayer;


    public Player(int lives, int diamonds) {
        this.lives = lives;
        this.diamands = diamonds;
    }

    public Player() {
        lives = 3;
    }



    public Position getPosition() {
        return positionPlayer;
    }

    public void setPosition(Position position) {
        this.positionPlayer = position;
    }


    public int getLives() {
        return lives;
    }

    public void setDiamonds(int diamonds) {
        this.diamands = diamonds;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getDiamonds() {
        return diamands;
    }

}
