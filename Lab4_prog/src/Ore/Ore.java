package Ore;

/** Just a class for mining some Ore, you know.
 *  Like we all did in a Minecraft in the good old days. */
public abstract class Ore {
    private String type;
    public Ore(String type) {
        this.type = type;
        System.out.println("Сгенерировалась горная порода " + type);
    }

    public String getType(){
        return this.type;
    }
}
