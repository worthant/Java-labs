package moves;

import ru.ifmo.se.pokemon.*;

public class HornLeech extends PhysicalMove {
    public HornLeech() {
        super(Type.GRASS, 75, 100);
    }

    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP,  50);
    }

    @Override
    protected String describe() {
        return "is using Horn Leech";
    }
}
