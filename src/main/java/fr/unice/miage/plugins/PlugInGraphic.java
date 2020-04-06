package fr.unice.miage.plugins;

import fr.unice.miage.game.gui.CanvasGUI;
import fr.unice.miage.sprite.Sprite;

public interface PlugInGraphic {
    public void draw(CanvasGUI canvas);
    public Sprite getPlayerSprite();
}
