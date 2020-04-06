package fr.unice.miage.plugins;

import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.sprite.Sprite;

public interface PlugInMovement {
    public void move(Sprite sprite, double time, GameBoard b);
}
