package characters;

import CosmicObjects.*;
import actions.Viewable;

public abstract class Human {
    private String name;
    private int id;
    private CosmicObject location;


    public Human(String name, int id, CosmicObject location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location.getViewPoint();
    }

    public void see(Viewable target, CosmicObject viewPoint) {
        System.out.println(this.name + " увидел " + target.getView(viewPoint));
    }

    public void read(String text) {
        System.out.println(this.name + " прочитал " + text);
    }

    public void write(String text) {
        System.out.println(this.name + " написал " + text);
    }

    public void draw(String figure) {
        System.out.println(this.name + " нарисовал " + figure);
    }

    @Override
    public int hashCode() {
        return 1000 * this.name.hashCode() - 7;
    }

    @Override
    public String toString() {
        return "Person: {"
                + "Parson name = '" + this.getName() + '\''
                + " Айдишник = " + this.hashCode()
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Human)) {
            return false;
        }
        Human c = (Human) o;
        return (id == c.id);
    }
}
