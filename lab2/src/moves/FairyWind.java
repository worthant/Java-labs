package moves;

import ru.ifmo.se.pokemon.*;

public class FairyWind extends SpecialMove {
    public FairyWind() {
        super(Type.FAIRY, 40, 100);
    }

    @Override
    protected String describe() {
        return "is using Fairy Wind";
    }
}
