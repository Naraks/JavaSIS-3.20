package pro.it.sis.javacourse;

public class Sword extends Weapon implements PhysicalBehavior {

    public Sword(int physicalDamage) {
        super(physicalDamage);
    }

    @Override
    void hit(Target target) {

    }

    @Override
    public int getPhysicalDamage() {
        return ;
    }
}
