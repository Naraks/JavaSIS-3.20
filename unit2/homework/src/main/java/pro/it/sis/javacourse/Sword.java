package pro.it.sis.javacourse;

import lombok.Getter;

import java.util.Set;

@Getter
public abstract class Sword implements WeaponBehavior {

    protected String name;
    protected Set<Damage> allDamage;

    public Sword(String name, Set<Damage> allDamage) {
        this.name = name;
        this.allDamage = allDamage;
    }

    @Override
    public void hit(Creature creature) {
        System.out.printf("You poked the %s with your %s\n", creature.getName(), name);
        creature.takeDamage(allDamage);
    }
}
