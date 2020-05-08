package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;

public interface PlugInWeapon extends PlugIn {

    //public List<Projectile> getWeaponProjectiles();

     void moveProjectile(Projectile projectile);

     void shot(Player player, long currentTime);

     void draw(CanvasGUI canvas, Projectile projectile);


    void onProjectileOut(String axis, Projectile projectile);

    void setPlayerImpact(Player player);
}
