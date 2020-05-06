package fr.unice.miage.common.sprite;

import fr.unice.miage.common.game_objects.Player;
import javafx.scene.paint.Paint;

public class RectangleProjectile {
    protected double width;
    protected double height;
    protected Paint color;

    public RectangleProjectile(Player player, double width, double height, Paint color){
        this.width = width;
        this.height = height;
        this.color = color;
    }
}
