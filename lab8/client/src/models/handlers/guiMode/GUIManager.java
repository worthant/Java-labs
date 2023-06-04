package models.handlers.guiMode;

import client.DataHolder;
import exceptions.BuildObjectException;
import models.*;
import models.handlers.ModeManager;

public class GUIManager implements ModeManager<City> {

    @Override
    public City buildObject() throws BuildObjectException {
        try {
            return DataHolder.getInstance().getCityObject();
        } catch (Exception e) {
            throw new BuildObjectException("Возникла ошибка в GUIManager");
        }
    }
}
