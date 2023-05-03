package models.validators;

import models.Coordinates;
import models.City;

import java.util.Optional;

/**
 * Implementation of validator for City
 *
 *  @since 2.0
 *  @author boris
 */
public class CityValidator implements Validator<City>{
    @Override
    public boolean validate(City city) {
        return new NameValidator().validate(city.getName())
                && new CoordinateXValidator().validate(Optional.of(city).map(City::getCoordinates).map(Coordinates::getX).orElse(null))
                && new CoordinateYValidator().validate(Optional.of(city).map(City::getCoordinates).map(Coordinates::getY).orElse(null))
                && new MetersAboveSeaLevelValidator().validate(city.getMetersAboveSeaLevel())
                && new PopulationValidator().validate(city.getPopulation())
                && new AreaValidator().validate(city.getArea());

    }

    @Override
    public String getDescr() {
        return "Validates City object";
    }
}
