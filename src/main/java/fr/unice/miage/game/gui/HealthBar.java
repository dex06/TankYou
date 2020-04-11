package fr.unice.miage.game.gui;

import fr.unice.miage.game_objects.Player;
import javafx.scene.paint.Paint;

public class HealthBar {

    private int width = 50;
    private int height = 10;
    private Paint color;

    public HealthBar(int width, int height, Paint color){
        this.width = width;
        this.height = height;
        this.color = color;
    }


    public void draw(Player player, CanvasGUI canvas){
        canvas.drawHealthBar(player, this.width, this.height, this.color);
    }
}
