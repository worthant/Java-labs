package CosmicObjects;

public class Sun extends CosmicObject{
    public Sun(String name, int id, int brightness, double radius, double mass){
        super(name, radius, mass, CosmicObjectType.STAR, id, brightness);
    }
}
