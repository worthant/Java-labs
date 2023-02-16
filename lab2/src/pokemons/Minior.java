package pokemons;

import moves.*;
import ru.ifmo.se.pokemon.*;

public class Minior extends Pokemon {
    public Minior(String name, int level) {
        super(name, level);
        setStats(60, 60, 100, 60, 100, 60);
        setType(Type.ROCK, Type.FLYING);
        setMove(new Confide(), new Swagger(), new MetalSound(), new ShadowBall());
    }
}
