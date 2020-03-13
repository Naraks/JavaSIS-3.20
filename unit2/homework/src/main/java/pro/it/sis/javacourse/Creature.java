package pro.it.sis.javacourse;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class Creature implements TargetBehavior {
    private String name;
    private int maxHitPoints;
    private int currentHitPoints;
    private Set<DamageType> immunity;

    private Creature() {
    }

    public Creature(String name, int hitPoints, Set<DamageType> immunity) {
        this.name = name;
        this.maxHitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
        this.immunity = immunity;
    }

    @Override
    public void takeDamage(Set<Damage> allDamage) {
        for (Damage dmg : allDamage) {
            if (immunity.contains(dmg.getDamageType())) {
                System.out.printf("%s immune to %s\n", name, dmg.getDamageType());
            } else currentHitPoints -= dmg.getAmount();
        }
    }
}
