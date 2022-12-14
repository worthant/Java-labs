package PlanetObjects;

import Exceptions.WeightException;

public class Rocket extends PlanetObject{
    private int weight = 300000;
    public Rocket(String name, int id) {
        super(name, id);
    }

    public int getWeight(){
        return this.weight;
    }

    public void setWeight(int weight) throws WeightException {
        if (weight > 0) {
            this.weight = weight;
        } else {
            throw new WeightException("У тебя чё объект настолько похудел что у него массы нет теперь? Или может она вообще отрицательная? Чё за кек, давай по новой.");
        }
    }
}
