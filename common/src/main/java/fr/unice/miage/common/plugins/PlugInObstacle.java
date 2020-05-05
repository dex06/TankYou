package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Obstacle;
import fr.unice.miage.common.game_objects.Player;

import java.util.List;

public interface PlugInObstacle extends PlugIn {

    void draw(CanvasGUI canvas,  Obstacle obstacle);

    List<Obstacle> generate();

}
