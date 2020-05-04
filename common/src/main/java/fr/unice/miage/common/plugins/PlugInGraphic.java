package fr.unice.miage.common.plugins;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.game_objects.Player;
import fr.unice.miage.common.sprite.Sprite;

public interface PlugInGraphic extends PlugIn {

    Sprite getPlayerSprite();

    void init(Player player);

    void setToDead();

    void draw(CanvasGUI canvas);
}
