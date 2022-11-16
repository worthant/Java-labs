package characters;

import CosmicObjects.CosmicObject;

public class Znaika extends Human {
    public Znaika(String name, String id, CosmicObject location) {
        super(name, id, location);
        System.out.println("Создан персонаж " + name);
    }

    public String getLocation() {
        return this.location.getname();
    }

}

