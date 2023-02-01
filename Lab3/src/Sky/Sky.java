package Sky;

import CosmicObjects.CosmicObject;
import actions.Viewable;

public class Sky implements Viewable {
    public Sky(){}

    public String getView(CosmicObject viewPoint){
        return viewPoint.getViewPoint();
    }
}
