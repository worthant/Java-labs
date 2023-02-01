package characters;

import CosmicObjects.CosmicObject;

public class Seledochka extends Human{
    public Seledochka(String name, int id, CosmicObject location) {
        super(name, id, location);
        System.out.println(name + " появился на свет");
    }
}
