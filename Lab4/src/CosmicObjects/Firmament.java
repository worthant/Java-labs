package CosmicObjects;

public class Firmament extends CosmicObject{
    public Firmament(String name, int id){
        super(name, CosmicObjectType.FIRMAMENT, id);
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
