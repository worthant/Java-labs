package collectionManagers.validators;

import collection.City.Human;

public class HumanValidator implements Validator<Human> {
    @Override
    public boolean validate(Human value) {
        if (value == null) {
            System.out.println("Введите ненулевое имя(не может быть null!)");
            return true;
        }
        return false;
    }
}
