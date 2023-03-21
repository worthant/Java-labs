package collectionManagers.validators;

import collection.City.City;
import collection.City.Coordinates;

import java.util.Optional;

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
