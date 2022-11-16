package characters;
import CosmicObjects.*;
import actions.Viewable;

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

    public String getLocation(){
        return this.location.getViewPoint();
    }

    public void see(Viewable target, CosmicObject viewPoint){
        System.out.println(this.name + " увидел " + target.getView(viewPoint));
    }

    public void read(String text){
        System.out.println(this.name + " прочитал " + text);
    }

    public void write(String text){
        System.out.println(this.name + " написал " + text);
    }

    public void draw(String figure){
        System.out.println(this.name + " нарисовал " + figure);
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
