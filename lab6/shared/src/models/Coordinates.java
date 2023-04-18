package models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class representing coordinates
 */
public class Coordinates implements Serializable {
    private Integer x; //Максимальное значение поля: 499
    private Double y; //Значение поля должно быть больше -274

    /**
     * Constructor for creating Coordinates object
     * @param x - coordinate x value
     * @param y - coordinate y value
     */
    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    /**
     * Compares two Coordinates objects by their x and y values
     * @param o - Coordinates object to compare
     * @return - negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    public int compareTo(Coordinates o) {
        if (o == null) {
            return 1;
        }
        int result = Integer.compare(this.x, o.x);
        if (result == 0)
            return Double.compare(this.y, o.y);
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates: " +
                "x = " + x +
                ", y = " + y;
    }
}