package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;

public interface PlugInWeapon extends PlugIn {

    //public List<Projectile> getWeaponProjectiles();

     void moveProjectile(Projectile projectile);

     void shoot(Player player);

     void draw(CanvasGUI canvas, Projectile projectile);


    void onProjectileOut(String axis, Projectile projectile);

    void applyPlayerImpact(Projectile projectile, Player player);

    void applyObstacleCollision(Projectile projectile, String inversion);
}
