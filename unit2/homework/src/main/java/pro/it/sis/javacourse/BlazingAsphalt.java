package pro.it.sis.javacourse;

import java.util.Collections;
import java.util.HashSet;

public class BlazingAsphalt extends Sword {

    public BlazingAsphalt() {
        super(Constants.BLAZING_ASPHALT, Collections.unmodifiableSet(new HashSet<Damage>() {{
            add(new Damage( DamageType.Fire, Constants.FIRE_DAMAGE));
            add(new Damage( DamageType.Physics, Constants.PHYSICS_DAMAGE));
        }}));
    }
}
