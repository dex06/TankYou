package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Projectile;
import fr.unice.miage.plugins.PlugInWeapon;

import java.util.ArrayList;
import java.util.List;

public class WeaponOne implements PlugInWeapon {

    private List<Projectile> weaponProjectiles = new ArrayList<>();

    public WeaponOne(){}


    public List<Projectile> getWeaponProjectiles() {
        return this.weaponProjectiles;
    }

    public void createWeapon(){ }
}
