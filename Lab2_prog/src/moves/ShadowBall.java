package moves;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove {
    public ShadowBall() {
        super(Type.GHOST, 80, 100);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        if (Math.random() < 0.2) {
            p.setMod(Stat.SPECIAL_DEFENSE, 2);
        }
    }

    @Override
    protected String describe() {
        return "is using Shadow Ball";
    }
}
