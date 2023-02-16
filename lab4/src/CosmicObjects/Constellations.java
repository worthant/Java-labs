package CosmicObjects;

public class Constellations extends CosmicObject{
    public Constellations(String name, int id){
        super(name, CosmicObjectType.CONSTELLATION, id);
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
