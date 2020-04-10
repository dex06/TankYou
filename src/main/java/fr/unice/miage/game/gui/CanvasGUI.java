package fr.unice.miage.game.gui;

import fr.unice.miage.game_objects.Player;
import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.RoundSprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CanvasGUI {
   private Canvas canvas;
   private GraphicsContext gc;

    public CanvasGUI(int width, int height){
        this.canvas = new Canvas(width, height);
        this.gc = this.canvas.getGraphicsContext2D();

    }

    public double getWidth(){
        return this.canvas.getWidth();
    }

    public double getHeight() {
        return this.canvas.getHeight();
    }

    public GraphicsContext getGraphicsContext(){
        return this.gc;
    }

    public Canvas getCanvas(){
        return this.canvas;
    }

    public void clean(){
        gc.setFill(Color.AZURE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.setFill(Color.BLACK);
    }

    public RectangleSprite createRectangleSprite(int x, int y, int speedX, int speedY){
        return new RectangleSprite(x, y, speedX, speedY);
    }

    public RoundSprite createRoundSprite(int x, int y, int speedX, int speedY, int diameter){
        return new RoundSprite(x, y , speedX, speedY, diameter);
    }

    public void drawHealthBar(Player player, int width, int height, Paint color) {
        double x = player.getPosition().getX();
        double y = player.getPosition().getY();
        double health = player.getHealth();
        double healthWidth = width * health / 100;
        gc.setFill(Color.BLUE);
        gc.fillRect(x, y, width, height);
        gc.setFill(color);
        gc.fillRect(x, y, healthWidth, height);

    }
}
