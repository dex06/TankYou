package fr.unice.miage.uncompiled;

import fr.unice.miage.game_objects.Projectile;
import fr.unice.miage.plugins.PlugInWeapon;

import java.util.List;

public class WeaponOne implements PlugInWeapon {

    private List<Projectile> weaponProjectiles;

    public WeaponOne(){}


    public List<Projectile> getWeaponProjectiles() {
        return this.weaponProjectiles;
    }
}
