package CosmicObjects;

public class Moon extends CosmicObject{
    public Moon(String name, int id, int brightness, double radius, double mass){
        super(name, radius, mass, CosmicObjectType.SATELLITE, id, brightness);
    }
}
