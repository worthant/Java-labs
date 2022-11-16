package CosmicObjects;

import actions.Lightable;

public class Sun extends CosmicObject implements Lightable {
    public Sun(String name, String id, int brightness, double radius, double mass, String time){
        super(name, radius, mass, CosmicObjectType.STAR, id, brightness, time);
    }

    public String light(int light){
        return this.name + " светит с силой " + Integer.toString(light);
    }
}
