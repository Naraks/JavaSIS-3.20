package pro.it.sis.javacourse;


public abstract class Weapon {

    private int damage;

    public Weapon(int damage){
        this.damage = damage;
    }

    abstract void hit(Target target);
}
