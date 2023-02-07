package collection;

import java.util.Objects;

public class Coordinates {
    private int x; //Максимальное значение поля: 499
    private double y; //Значение поля должно быть больше -274

    public Coordinates(int x, double y) {
        this.x = x;
        this.y = y;
    }

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
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}