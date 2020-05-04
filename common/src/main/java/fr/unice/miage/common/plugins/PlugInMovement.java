package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Player;

public interface PlugInMovement extends PlugIn {

    void move(Player player);

    void init(Player player);
}
