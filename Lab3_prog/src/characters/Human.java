package characters;
import CosmicObjects.*;
public abstract class Human {
    private String name;
    private String id;
    private CosmicObject location;


    public Human(String name, String id, CosmicObject location) {
        this.name = name;
        this.id = id;
        this.location = location;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public int hashCode() {
        return (id != null ? id.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "Character Data: {"
                + "Имя персонажа = '" + this.getName() + '\''
                + " Айдишник = " + Integer.toString(this.hashCode() - 48)
                + '}';

    }
}
