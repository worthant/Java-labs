package collection;

public class Human implements Comparable<Human> {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String zodiacSign;

    public Human(String name, String zodiacSign) {
        this.name = name;
        this.zodiacSign = zodiacSign;
    }

    @Override
    public int compareTo(Human o) {
        if (o == null) {
            return 1;
        }
        if (this.name != null) {
            return this.name.compareTo(o.name);
        } else {
            if (this.zodiacSign != null) {
                return this.zodiacSign.compareTo(o.zodiacSign);
            } else if (o.zodiacSign != null) {
                return -o.zodiacSign.compareTo(this.zodiacSign);
            }
        }
        return 0;
    }
    
    @Override
    public String toString() {
        return "Human: " +
                "name = '" + name + "'" +
                ", zodiacSign = " + zodiacSign;
    }
}
