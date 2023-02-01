package Animals;

public class Dinasour {
    private String name;
    private int age;
    public Dinasour(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void shoutOutLoud(){
        System.out.println("Shout Out Loud some shitty disrespectful words in terms of education");
    }

    public void eternallyDepressed(){
        System.out.println("Eternally Depressed");
    }

    public void feelsBadMan(){
        System.out.println("feels bad man");
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }

    @Override
    public String toString() {
        return "Dino{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
