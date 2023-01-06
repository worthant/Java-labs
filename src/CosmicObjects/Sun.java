package CosmicObjects;

import Exceptions.SoundException;
import Interfaces.Sound;

public class Sun extends CosmicObject implements Sound {
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

    @Override
    public void sound() throws SoundException {
        throw new SoundException("В безвоздушной среде звук не распространяется, мда, надо было на физику ходить в школе.");
    }
}
