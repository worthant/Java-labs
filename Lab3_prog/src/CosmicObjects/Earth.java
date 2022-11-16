package CosmicObjects;

import actions.Lightable;
import actions.Reflectable;

public class Earth extends CosmicObject implements Reflectable, Lightable {
    public Earth(String name, String id, int brightness, double radius, double mass, String time){
        super(name, radius, mass, CosmicObjectType.PLANET, id, brightness, time);
    }

    public String reflect(int otherLight){
        return this.name + " отражает свет с силой " + Integer.toString(otherLight);
    }

    public String light(int light){
        return this.name + " светит с силой " + Integer.toString(light);
    }
}