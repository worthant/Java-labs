package moves;

import ru.ifmo.se.pokemon.*;

public class MetalSound extends StatusMove {
    public MetalSound() {
        super(Type.STEEL, 0, 85);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.SPECIAL_DEFENSE, -2);
    }

    @Override
    protected String describe() {
        return "is using Metal Sound";
    }
}
