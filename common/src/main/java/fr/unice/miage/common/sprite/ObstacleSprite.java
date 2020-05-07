package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ObstacleSprite extends Sprite {

    private Vector2 position;

    public ObstacleSprite(Vector2 position, double width, double height, Paint color) {
        super(width, height, color);
        this.position = position;
    }

    public void draw(CanvasGUI canvas){
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(color);
        gc.fillRect(position.getX(), position.getY(), width, height);
    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }
}
