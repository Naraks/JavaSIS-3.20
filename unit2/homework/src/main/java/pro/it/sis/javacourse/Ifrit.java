package pro.it.sis.javacourse;

import java.util.Collections;
import java.util.HashSet;

public class Ifrit extends Creature implements TargetBehavior {
    public Ifrit() {
        super(Constants.IFRIT, Constants.IFRIT_HP, Collections.unmodifiableSet(new HashSet<DamageType>() {{
            add(DamageType.Fire);
        }}));
    }
}
