package collection.City;

public class Human implements Comparable<Human> {
    private String name; //Поле не может быть null, Строка не может быть пустой

    public Human(String name) {
        this.name = name;
    }

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
