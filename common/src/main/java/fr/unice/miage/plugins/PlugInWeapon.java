package fr.unice.miage.plugins;

import fr.unice.miage.game_objects.Projectile;

import java.util.List;

public interface PlugInWeapon {

    //public List<Projectile> getWeaponProjectiles();

    public void moveProjectile(Projectile projectile);

    public void checkProjectileOut(List<Projectile> projectile);


}
