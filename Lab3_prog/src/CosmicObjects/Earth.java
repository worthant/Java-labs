package CosmicObjects;

public class Earth extends CosmicObject{
    public Earth(String name, int id, int brightness, double radius, double mass){
        super(name, radius, mass, CosmicObjectType.PLANET, id, brightness);
    }
}
