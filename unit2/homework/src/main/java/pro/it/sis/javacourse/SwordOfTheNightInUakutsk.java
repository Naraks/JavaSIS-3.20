package pro.it.sis.javacourse;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;

@Getter
public class SwordOfTheNightInUakutsk extends Sword {

    public SwordOfTheNightInUakutsk() {
        super(Constants.SWORD_OF_THE_NIGHT_IN_YAKUTSK, Collections.unmodifiableSet(new HashSet<Damage>() {{
            add(new Damage( DamageType.Cold, Constants.COLD_DAMAGE));
            add(new Damage( DamageType.Physics, Constants.PHYSICS_DAMAGE));
        }}));
    }

}

