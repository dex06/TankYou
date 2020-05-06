package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;

import java.util.List;

public interface PlugInWeapon extends PlugIn {

    //public List<Projectile> getWeaponProjectiles();

    public void moveProjectile(Projectile projectile);

    public void checkProjectileOut(List<Projectile> projectile);

    public void shot(Player player, long currentTime);

    public void draw(CanvasGUI canvas, List<Projectile> projectile);


}
