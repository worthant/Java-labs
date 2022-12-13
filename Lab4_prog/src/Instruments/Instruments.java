package Instruments;

/** This class really helps mining some Ore. */
public abstract class Instruments {
    private String type;
    public Instruments(String type) {
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public String bang() {
        return "Стучать";
    }
}

