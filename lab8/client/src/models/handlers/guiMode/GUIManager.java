package models.handlers.guiMode;

import client.DataHolder;
import exceptions.BuildObjectException;
import main.Utilities;
import models.*;
import models.handlers.ModeManager;
import models.validators.CityValidator;
import models.validators.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;

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
