package moves;

import ru.ifmo.se.pokemon.*;

public class BulkUp extends StatusMove {
    public BulkUp() {
        super(Type.FIGHTING, 0, 0);
    }

    @Override
    protected void applyOppEffects(Pokemon p) {
        p.setMod(Stat.ATTACK, 1);
        p.setMod(Stat.DEFENSE, 1);
    }

    @Override
    protected String describe(){
        return "is using Bulk Up";
    }
}
