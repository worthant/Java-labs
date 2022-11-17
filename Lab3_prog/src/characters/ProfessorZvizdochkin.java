package characters;

import CosmicObjects.CosmicObject;

public class ProfessorZvizdochkin extends Human{
    public ProfessorZvizdochkin(String name, int id, CosmicObject location) {
        super(name, id, location);
        System.out.println(name + " появился на свет");
    }
}
