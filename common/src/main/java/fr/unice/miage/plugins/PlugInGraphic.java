package fr.unice.miage.plugins;

import fr.unice.miage.CanvasGUI;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.sprite.Sprite;

public interface PlugInGraphic {

    Sprite getPlayerSprite();

    void init(Player player);

    void setToDead();

    void draw(CanvasGUI canvas);
}
