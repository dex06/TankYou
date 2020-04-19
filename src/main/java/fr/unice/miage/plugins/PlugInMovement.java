package fr.unice.miage.plugins;

import fr.unice.miage.game_objects.Player;

public interface PlugInMovement {

    void move(Player player);

    void init(Player player);
}
