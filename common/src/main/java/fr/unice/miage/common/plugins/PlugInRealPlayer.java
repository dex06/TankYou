package fr.unice.miage.common.plugins;

import fr.unice.miage.common.game_objects.Player;
import javafx.scene.Scene;

public interface PlugInRealPlayer extends PlugIn {

    void handleKeyboardEvent(Player player, Scene scene);

    void handleMouseEvent(Player player, Scene scene);
}
