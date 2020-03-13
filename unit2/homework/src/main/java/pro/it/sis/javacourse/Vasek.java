package pro.it.sis.javacourse;

import java.util.Collections;
import java.util.HashSet;

public class Vasek extends Creature implements TargetBehavior {
    public Vasek() {
        super(Constants.VASEK, Constants.VASEK_HP, Collections.unmodifiableSet(new HashSet<DamageType>() {{
            add(DamageType.Fire);
            add(DamageType.Cold);
            add(DamageType.Physics);
        }}));
    }
}