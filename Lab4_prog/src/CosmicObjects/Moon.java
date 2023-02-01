package CosmicObjects;

import Exceptions.SoundException;
import Interfaces.Sound;

public class Moon extends CosmicObject implements Sound{
    public Moon(String name, int id, int brightness, double radius, double mass, String time){
        super(name, radius, mass, CosmicObjectType.SATELLITE, id, brightness, time);
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
    public void sound() throws SoundException{
        throw new SoundException("В безвоздушной среде звук не распространяется, мда, надо было на физику ходить в школе.");
    }

}
