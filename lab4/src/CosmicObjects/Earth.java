package CosmicObjects;

import Interfaces.Shine;
import Interfaces.Sound;

public class Earth extends CosmicObject implements Sound {
    public Earth(String name, int id, int brightness, double radius, double mass, String time){
        super(name, radius, mass, CosmicObjectType.PLANET, id, brightness, time);
    }

    @Override
    public String reflect(int otherLight){
        return this.getName() + " отражает свет с силой " + otherLight;
    }

    @Override
    public String light(int light){
        return this.getName() + " светит с силой " + light;
    }

    @Override
    public void sound(){
        System.out.println("Звук распространяется в среде" + this.getName());
    }

    public static String diskShine() {
        Shine disk = new Shine() {
            @Override
            public String shine() {
                return "светящийся диск";
            }
        };
        return disk.shine();
    }
}
