package fr.unice.miage.uncompiled;

import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.sprite.Sprite;
import javafx.scene.layout.FlowPane;

public class MoveTwo implements PlugInMovement {
    public MoveTwo(){}
    public void move(Sprite sprite, FlowPane healthBar, double time, GameBoard b){

        sprite.update(time, b);
        healthBar.setLayoutX(sprite.getX() - 15);
        healthBar.setLayoutY(sprite.getY() - 15);
        healthBar.setOpacity(1);
        b.addHealthBar(healthBar);
    }


}
