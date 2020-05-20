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
    private int rotation = 0;

    public ObstacleSprite(Vector2 position, double width, double height, Paint color) {
        super(width, height, color);
        this.position = position;
    }

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, Image img) {
        super(width, height, color);
        this.position = position;
        this.imgSprite = img;
    }

    public ObstacleSprite(Vector2 position, double width, double height, Paint color, Image img, int rotation) {
        super(width, height, color);
        this.position = position;
        this.imgSprite = img;
        this.rotation = rotation;
    }

    public void draw(CanvasGUI canvas){
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.save();
        if(imgSprite == null){
            gc.setFill(color);
            gc.fillRect(position.getX(), position.getY(), width, height);
        }
        else{
            gc.translate(this.position.getX(), this.position.getY());
            gc.rotate(rotation);
            gc.translate(-this.getWidth()/2, -this.getHeight()/2);
            gc.drawImage(imgSprite, 0, 0, width, height);
        }
        gc.restore();

        if(this.rotation >= 360)
            this.rotation = 0;
        else
            this.rotation ++;
    }

    @Override
    public Shape getBoundingShape() {
        return new Rectangle(position.getX(), position.getY(), width, height);
    }
}
