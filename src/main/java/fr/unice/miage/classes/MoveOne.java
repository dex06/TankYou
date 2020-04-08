package fr.unice.miage.classes;

import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.layout.FlowPane;

public class MoveOne implements PlugInMovement {
    public MoveOne(){}
    public void move(Sprite sprite, FlowPane healthBar, double time, GameBoard b){

        sprite.update(time, b);
        healthBar.setLayoutX(sprite.getX() - 15);
        healthBar.setLayoutY(sprite.getY() - 15);
        healthBar.setOpacity(1);
        b.addHealthBar(healthBar);
    }


}
