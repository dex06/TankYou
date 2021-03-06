package fr.unice.miage.common;

import fr.unice.miage.common.game_objects.Player;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CanvasGUI {
   private final Canvas canvas;
   private final GraphicsContext gc;

    public CanvasGUI(double width, double height){
        this.canvas = new Canvas(width, height);
        this.gc = this.canvas.getGraphicsContext2D();

    }

    public double getWidth(){
        return canvas.getWidth();
    }

    public double getHeight() {
        return canvas.getHeight();
    }

    public GraphicsContext getGraphicsContext(){
        return gc;
    }

    public Canvas getCanvas(){
        return canvas;
    }

    public void clean(){
        gc.clearRect(0,0,canvas.getWidth(), canvas.getHeight());
    }

    public void drawHealthBar(Player player, double width, double height, Paint color) {
        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        double health = player.getHealth();
        double healthWidth = width * health / 100;
        double rectifyX = -5;
        double rectifyY = -10;
        gc.save();
        gc.setFill(Color.BLUE);
        gc.fillRect(x + rectifyX, y + rectifyY, width, height);
        gc.setFill(color);
        gc.fillRect(x + rectifyX, y + rectifyY, healthWidth, height);
        gc.restore();
    }
}
