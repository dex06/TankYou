package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;

import java.util.List;

public interface PlugInObstacle {

    void draw(CanvasGUI canvas);

    void generate();
}
