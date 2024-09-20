package g58414.atlg.boulder.model.elements;

/**
 * Directions to move
 */
public enum Directions {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private int x;
    private int y;

    /**
     * constructor of the class directions
     *
     * @param dx x position in the chosen direction
     * @param dy y position in the chosen direction
     */
    Directions(int dx, int dy) {
        this.x = dx;
        this.y = dy;
    }

    /**
     * getter for the variable x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * getter for the variable y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

}
