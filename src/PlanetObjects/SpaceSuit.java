package PlanetObjects;

import Colors.Color;
import Exceptions.WeightException;

public class SpaceSuit extends PlanetObject{
    private int weight = 130;
    public SpaceSuit(String name, int id, Color color) {
        super(name, id, color);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) throws WeightException {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new WeightException("У тебя чё объект настолько похудел что у него массы нет теперь? Или может она вообще отрицательная? Чё за кек, давай по новой.");
        }
    }
}
