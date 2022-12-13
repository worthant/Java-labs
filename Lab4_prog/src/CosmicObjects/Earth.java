package CosmicObjects;

import Interfaces.Shine;

public class Earth extends CosmicObject {
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

    public static void diskShine() {
        Shine disk = new Shine() {
            @Override
            public void shine() {
                System.out.println("светящийся диск");
            }
        };
        disk.shine();
    }
}
