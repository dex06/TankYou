package fr.unice.miage.classes;

import fr.unice.miage.game.gui.GameBoard;
import fr.unice.miage.plugins.PlugInMovement;
import fr.unice.miage.sprite.Sprite;

public class MoveOne implements PlugInMovement {
    public MoveOne(){}
    public void move(Sprite sprite, double time, GameBoard b){
        sprite.update(time, b);
    }


}
