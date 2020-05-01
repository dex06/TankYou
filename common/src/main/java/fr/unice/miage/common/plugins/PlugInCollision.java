package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Player;

import java.util.List;

public interface PlugInCollision {

    void checkAllCollisions(List<Player> players);
}
