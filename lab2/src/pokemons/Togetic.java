package pokemons;

import moves.*;

public class Togetic extends Togepi {
    public Togetic(String name, int level) {
        super(name, level);
        setStats(55, 40, 85, 80, 105, 40);
        addMove(new FairyWind());
    }
}