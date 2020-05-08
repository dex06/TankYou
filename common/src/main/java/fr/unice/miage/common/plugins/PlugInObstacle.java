package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.game_objects.Projectile;

import java.util.List;

public interface PlugInObstacle extends PlugIn {

    List<Obstacle> generate();

    void setPlayerCollision(Player player);

    void setWeaponCollision(Projectile projectile);
}
