package characters;

import CosmicObjects.CosmicObject;

public class Znaika extends Human {
    public Znaika(String name, int id, CosmicObject location) {
        super(name, id, location);
        System.out.println(name + " появился на свет");
    }
}

