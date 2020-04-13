package fr.unice.miage.plugins;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.game_objects.Player;
import fr.unice.miage.sprite.Sprite;

public interface PlugInGraphic {
    void draw(CanvasGUI canvas);

    Sprite getPlayerSprite();

    void init(Player player);

    void setToDead();
}
