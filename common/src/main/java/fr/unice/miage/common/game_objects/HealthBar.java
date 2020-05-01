package fr.unice.miage.common.game_objects;

import fr.unice.miage.common.CanvasGUI;
import javafx.scene.paint.Paint;

public class HealthBar {

    private int width = 30;
    private int height = 5;
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
