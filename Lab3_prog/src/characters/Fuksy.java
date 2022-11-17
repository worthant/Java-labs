package characters;

import CosmicObjects.CosmicObject;

public class Fuksy extends Human{
    public Fuksy(String name, int id, CosmicObject location){
        super(name, id, location);
        System.out.println(name + " появился на свет");
    }
}
