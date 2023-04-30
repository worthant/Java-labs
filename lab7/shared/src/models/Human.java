package models;

import java.io.Serializable;

/**
 * Class representing a human
 */
public class Human implements Comparable<Human>, Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой

    public Human(String name) {
        this.name = name;
    }
    public Human(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Compares two Human objects by their names
     * @param o - Human object to compare
     * @return - negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object
     */
    @Override
    public int compareTo(Human o) {
        if (o == null) {
            return 1;
        }
        if (this.name != null) {
            return this.name.compareTo(o.name);
        }
        return 0;
    }

    @Override
    public String toString() {
        return name;
    }
}
