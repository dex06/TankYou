package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Projectile;

import java.util.List;

public interface PlugInWeapon extends PlugIn {

    //public List<Projectile> getWeaponProjectiles();

    public void moveProjectile(Projectile projectile);

    public void checkProjectileOut(List<Projectile> projectile);


}
