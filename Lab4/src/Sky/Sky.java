package Sky;

import CosmicObjects.CosmicObject;
import Interfaces.Viewable;

public class Sky implements Viewable {
    public Sky() {
        System.out.println("Генерация атмосферы");
    }

    public String getView(CosmicObject viewPoint){
        return viewPoint.getName();
    }

    public String getPicture(String picture) {
        return picture;
    }
}
