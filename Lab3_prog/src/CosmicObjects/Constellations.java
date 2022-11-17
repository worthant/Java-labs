package CosmicObjects;

public class Constellations extends CosmicObject{
    public Constellations(String name){
        super(name, CosmicObjectType.CONSTELLATION);
    }

    @Override
    public String light(int light) {
        return null;
    }

    @Override
    public String reflect(int otherLight) {
        return null;
    }
}
