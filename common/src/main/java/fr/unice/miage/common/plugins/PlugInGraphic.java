package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;

public interface PlugInGraphic extends PlugIn {


    void init(Player player);

    void setToDead(Player player);

    void draw(Player player, CanvasGUI canvas);
}
