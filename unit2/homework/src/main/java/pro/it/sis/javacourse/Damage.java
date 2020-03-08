package pro.it.sis.javacourse;

import lombok.Getter;

@Getter
public class Damage {
    private DamageType damageType;
    private int amount;

    public Damage(DamageType damageType, int amount) {
        this.damageType = damageType;
        this.amount = amount;
    }
}
