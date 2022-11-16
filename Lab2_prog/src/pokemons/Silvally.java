package pokemons;

import ru.ifmo.se.pokemon.*;
import moves.*;

public class Silvally extends Pokemon {
    public Silvally(String name, int level) {
        super(name, level);
        setStats(95, 95, 95, 95,95, 95);
        setType(Type.NORMAL);
        setMove(new Confide(), new Swagger(), new MetalSound());
    }
}
