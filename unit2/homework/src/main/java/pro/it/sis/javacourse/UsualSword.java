package pro.it.sis.javacourse;

import lombok.Getter;

import java.util.Collections;
import java.util.HashSet;

@Getter
public class UsualSword extends Sword implements WeaponBehavior {

    public UsualSword() {
        super(Constants.USUAL_SWORD, Collections.unmodifiableSet(new HashSet<Damage>() {{
            add(new Damage( DamageType.Physics, Constants.PHYSICS_DAMAGE));
        }}));
    }
}
