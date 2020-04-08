package fr.unice.miage.plugins;

import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.layout.FlowPane;

public interface PlugInMovement {
    public void move(Sprite sprite, FlowPane healthbar, double time, GameBoard b);
}
