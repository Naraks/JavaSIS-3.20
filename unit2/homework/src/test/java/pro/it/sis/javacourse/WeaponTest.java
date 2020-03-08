package pro.it.sis.javacourse;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WeaponTest {

    @Test
    public void testDamageUsualSwordVsIceGiant() {

        Creature t = new IceGiant();
        Sword w = new UsualSword();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageUsualSwordVsIfrit() {

        Creature t = new Ifrit();
        Sword w = new UsualSword();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageUsualSwordVsVasek() {

        Creature t = new Vasek();
        Sword w = new UsualSword();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageUakutskSwordVsIceGiant() {

        Creature t = new IceGiant();
        Sword w = new SwordOfTheNightInUakutsk();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageUakutskSwordVsIfrit() {

        Creature t = new Ifrit();
        Sword w = new SwordOfTheNightInUakutsk();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageUakutskSwordVsVasek() {

        Creature t = new Vasek();
        Sword w = new SwordOfTheNightInUakutsk();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageBlazingSwordVsIceGiant() {

        Creature t = new IceGiant();
        Sword w = new BlazingAsphalt();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageBlazingSwordVsIfrit() {

        Creature t = new Ifrit();
        Sword w = new BlazingAsphalt();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }

    @Test
    public void testDamageBlazingSwordVsVasek() {

        Creature t = new Vasek();
        Sword w = new BlazingAsphalt();
        w.hit(t);

        assertEquals(t.getMaxHitPoints() - w.allDamage.stream().filter(i ->
                !t.getImmunity().contains(i.getDamageType())).mapToInt(Damage::getAmount).sum(), t.getCurrentHitPoints());
    }
}