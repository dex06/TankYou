package fr.unice.miage.plugins;

import fr.unice.miage.game_objects.Player;

import java.util.List;

public interface PlugInCollision {

    void checkAllCollisions(List<Player> players);
}
