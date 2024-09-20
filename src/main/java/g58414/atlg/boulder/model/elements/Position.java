package g58414.atlg.boulder.model.elements;

import java.util.Objects;

/**
 * Position of the elements
 */
public class Position {
    private int x;
    private int y;

    /**
     * constructor of the position
     *
     * @param x variable x of the position
     * @param y variable y of the position
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * getter of the variable x
     *
     * @return x
     */
    public int getX() {
        return x;
    }

    /**
     * getter of the variable y
     *
     * @return y
     */
    public int getY() {
        return y;
    }

    /**
     * creates new position to te given direction
     *
     * @param dir
     * @return
     */
    public Position next(Directions dir) {
        return new Position(x + dir.getX(), y + dir.getY());
    }

    /**
     * generated equals
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    /**
     * generated hashcode
     *
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
