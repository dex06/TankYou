package fr.unice.miage.game.gui;

import fr.unice.miage.sprite.RectangleSprite;
import fr.unice.miage.sprite.RoundSprite;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

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

    public RectangleSprite createRectangleSprite(int x, int y, int speedX, int speedY){
        return new RectangleSprite(x, y, speedX, speedY);
    }

    public RoundSprite createRoundSprite(int x, int y, int speedX, int speedY, int diameter){
        return new RoundSprite(x, y , speedX, speedY, diameter);
    }
}
