package CosmicObjects;

public class Firmament extends CosmicObject{
    public Firmament(String name){
        super(name, CosmicObjectType.FIRMAMENT);
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
