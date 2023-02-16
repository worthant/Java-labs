package pokemons;

import moves.*;

public class Togekiss extends Togetic{
    public Togekiss(String name, int level) {
        super(name, level);
        setStats(85, 50, 95, 120, 115, 80);
        addMove(new SteelWing());
    }
    
}
