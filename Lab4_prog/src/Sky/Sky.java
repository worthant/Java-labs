package Sky;

import CosmicObjects.CosmicObject;
import Interfaces.Viewable;

public class Sky implements Viewable {
    public String getView(CosmicObject viewPoint){
        return viewPoint.getViewPoint();
    }
}
