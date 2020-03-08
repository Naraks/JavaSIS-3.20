package pro.it.sis.javacourse;

import java.util.Collections;
import java.util.HashSet;

public class IceGiant extends Creature implements TargetBehavior {

    public IceGiant() {
        super(Constants.ICE_GIANT, Constants.ICE_GIANT_HP, Collections.unmodifiableSet(new HashSet<DamageType>() {{
            add(DamageType.Cold);
        }}));
    }
}
