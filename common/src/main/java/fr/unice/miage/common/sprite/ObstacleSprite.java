package fr.unice.miage.common.sprite;

import fr.unice.miage.common.CanvasGUI;
import fr.unice.miage.common.geom.Vector2;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ObstacleSprite extends Sprite {

    private final Vector2 position;
    private Image imgSprite = null;

    public ObstacleSprite(Vector2 position, double width, double height, Paint color) {
        super(width, height, color);
        this.position = position;
    }

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, Image img) {
        super(width, height, color);
        this.position = position;
        this.imgSprite = img;
    }

    public void draw(CanvasGUI canvas){
        GraphicsContext gc = canvas.getGraphicsContext();
        if(imgSprite == null){
            gc.setFill(color);
            gc.fillRect(position.getX(), position.getY(), width, height);
        }
        else{
            gc.drawImage(imgSprite, position.getX(), position.getY(), height, width);
        }

    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }
}
