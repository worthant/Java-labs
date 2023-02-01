package CosmicObjects;

import actions.Lightable;

public class Sun extends CosmicObject implements Lightable {
    public Sun(String name, int id, int brightness, double radius, double mass, String time){
        super(name, radius, mass, CosmicObjectType.STAR, id, brightness, time);
    }

    @Override
    public String light(int light){
        return this.getName() + " светит с силой " + light;
    }

    @Override
    public String reflect(int otherLight) {
        return null;
    }
}
